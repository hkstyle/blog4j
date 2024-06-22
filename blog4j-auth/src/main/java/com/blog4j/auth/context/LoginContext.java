package com.blog4j.auth.context;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/21 20:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginContext {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * SaTokenInfo
     */
    private SaTokenInfo saTokenInfo;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 验证码
     */
    private String captcha;
}
