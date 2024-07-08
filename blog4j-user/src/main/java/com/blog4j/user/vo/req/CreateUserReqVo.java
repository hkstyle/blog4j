package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 14:36
 **/
@Data
public class CreateUserReqVo {
    /**
     * 角色ID
     */
    @NotBlank(message = "用户角色不能为空")
    private String roleId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户头像
     */
    @NotBlank(message = "用户头像不能为空")
    private String avatar;

    /**
     * 组织ID
     */
    private String organizationId;
}
