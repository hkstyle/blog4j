package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 13:11
 **/
@Data
public class DeletePermissionNodeReqVo {
    /**
     * 父节点ID
     */
    @NotNull(message = "父节点ID不能为空")
    private Integer parentId;

    /**
     * 当前节点ID
     */
    @NotNull(message = "当前节点ID不能为空")
    private Integer permissionId;
}
