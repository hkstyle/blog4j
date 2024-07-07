package com.blog4j.auth.service;

import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.vo.resp.AesKeyAndIvRespVo;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/21 19:57
 **/
public interface AuthService {
    /**
     * 登录
     *
     * @param loginContext 登录信息
     */
    void login(LoginContext loginContext);

    /**
     * 退出登录
     *
     * @param userId 用户ID
     */
    void logout(String userId);

    /**
     * 获取AES前后端加解密的KEY和IV
     *
     * @return 前后端加解密的KEY和IV
     */
    AesKeyAndIvRespVo getAesKeyAndIv();
}
