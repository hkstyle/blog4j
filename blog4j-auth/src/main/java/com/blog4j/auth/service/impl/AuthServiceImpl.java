package com.blog4j.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.TypeReference;
import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.feign.UserFeignService;
import com.blog4j.auth.service.AuthService;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.FResult;
import com.blog4j.common.utils.RedisUtil;
import com.blog4j.common.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 认证服务
 * @Create on : 2024/6/21 19:57
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param loginContext 登录信息
     */
    @Override
    public void login(LoginContext loginContext) {
        // 判断验证码是否正确
        if (!this.checkCaptcha(loginContext)) {
            log.error("验证码校验失败");
            throw new Blog4jException(ErrorEnum.INVALID_CAPTCHA_ERROR);
        }

        // 远程调用user模块获取用户信息
        UserInfoVo userInfoVo = this.getUserInfo(loginContext);

        // TODO 校验密码 密码加密 更新用户最近一次的登录时间
        StpUtil.login(userInfoVo.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        loginContext.setSaTokenInfo(tokenInfo);
    }

    private UserInfoVo getUserInfo(LoginContext loginContext) {
        String userName = loginContext.getUserName();
        FResult result = userFeignService.getUserInfoByUserName(userName);
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            log.error("远程调用user模块获取用户信息失败, 失败原因：[{}]", message);
            throw new Blog4jException(code, message);
        }

        return result.getData(new TypeReference<UserInfoVo>() {
        });
    }


    private boolean checkCaptcha(LoginContext loginContext) {
        String captcha = loginContext.getCaptcha();
        String uuid = loginContext.getUuid();

        Object val = redisUtil.get(uuid);
        if (Objects.isNull(val)) {
            return false;
        }

        String captchaVal = (String) val;
        if (!StringUtils.equals(captcha, captchaVal)) {
            return false;
        }

        redisUtil.del(uuid);
        return true;
    }
}
