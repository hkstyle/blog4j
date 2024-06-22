package com.blog4j.auth.service.impl;

import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
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
    /**
     * 登录
     *
     * @param loginContext 登录信息
     */
    @Override
    public void login(LoginContext loginContext) {
        String userName = loginContext.getUserName();
        String password = loginContext.getPassword();
    }
}
