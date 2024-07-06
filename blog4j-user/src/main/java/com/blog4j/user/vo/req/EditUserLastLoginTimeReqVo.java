package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 15:09
 **/
@Data
public class EditUserLastLoginTimeReqVo {
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 用户最近一次登录时间
     */
    @NotBlank(message = "用户最近一次登录时间不能为空")
    private String lastLoginTime;
}
