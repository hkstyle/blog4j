package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 10:47
 **/
@Data
public class SaveRolePermissionRelReqVo {
    /**
     * 角色ID
     */
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    /**
     * 权限ID集合
     */
    @NotEmpty(message = "权限ID集合不能为空")
    private List<Integer> permissionIds;
}
