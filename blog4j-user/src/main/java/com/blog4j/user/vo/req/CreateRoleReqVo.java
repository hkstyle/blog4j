package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 22:45
 **/
@Data
public class CreateRoleReqVo {
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色代码
     */
    @NotBlank(message = "角色代码不能为空")
    private String roleCode;

    /**
     * 角色描述
     */
    @NotBlank(message = "角色描述不能为空")
    private String roleDesc;
}
