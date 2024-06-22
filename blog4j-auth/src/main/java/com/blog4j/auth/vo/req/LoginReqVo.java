package com.blog4j.auth.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 登录请求体
 * @Create on : 2024/6/21 19:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReqVo {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 1, max = 32, message = "用户名长度超出上限")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * UUID
     */
    @NotBlank(message = "UUID不能为空")
    private String uuid;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
