package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 19:27
 **/
@Data
public class CreateParentNodeReqVo {
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String permissionName;

    /**
     * 权限代码
     */
    @NotBlank(message = "权限代码不能为空")
    private String permissionCode;
}
