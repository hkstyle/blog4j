package com.blog4j.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.feign.UserFeignService;
import com.blog4j.auth.service.AuthService;
import com.blog4j.auth.utils.SecretUtils;
import com.blog4j.auth.vo.resp.AesKeyAndIvRespVo;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.FResult;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.RedisUtil;
import com.blog4j.common.utils.RsaUtil;
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
        String dbPassword = userInfoVo.getPassword();
        String reqPassword = SecretUtils.desEncrypt(loginContext.getPassword());
        String decrypt = RsaUtil.decrypt(dbPassword);
        if (!StringUtils.equals(decrypt, reqPassword)) {
            log.error("password error .");
            throw new Blog4jException(ErrorEnum.PASSWORD_ERROR);
        }

        // TODO 更新用户最近一次的登录时间
        StpUtil.login(userInfoVo.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        loginContext.setSaTokenInfo(tokenInfo);
    }

    /**
     * 退出登录
     *
     * @param userId 用户ID
     */
    @Override
    public void logout(String userId) {
        StpUtil.logout(userId);
    }

    /**
     * 获取AES前后端加解密的KEY和IV
     *
     * @return 前后端加解密的KEY和IV
     */
    @Override
    public AesKeyAndIvRespVo getAesKeyAndIv() {
        // TODO 调用系统服务获取
        return AesKeyAndIvRespVo.builder()
                .iv("63eeac68cf074c8c")
                .key("63eeac68cf074c8c")
                .build();
    }

    private UserInfoVo getUserInfo(LoginContext loginContext) {
        String userName = loginContext.getUserName();
        FResult result = userFeignService.getUserInfoByUserName(userName);
        return CommonUtil.getUserInfo(result);
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
