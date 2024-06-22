package com.blog4j.auth.service.impl;

import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.feign.UserFeignService;
import com.blog4j.auth.service.AuthService;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.FResult;
import com.blog4j.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 登录
     *
     * @param loginContext 登录信息
     */
    @Override
    public void login(LoginContext loginContext) {
        String userName = loginContext.getUserName();
        String password = loginContext.getPassword();
        FResult result = userFeignService.getUserInfoByUserName(userName);
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            throw new Blog4jException(code, message);
        }
        /*JSONObject jsonObject = JSONObject.parseObject(result.toString());
        Object code = jsonObject.get("code");
        log.info(result.toString());
        if (result.getCode() != CommonConstant.SUCCESS_CODE) {
            throw new Blog4jException(result.getCode(), result.getMessage());
        }
        log.info(String.valueOf(result));*/
//        StpUtil.login(userInfoVo.getUserId());
//        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
//        loginContext.setSaTokenInfo(tokenInfo);
    }
}
