package com.blog4j.api.vo;

import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 20:22
 **/
@Data
public class PermissionVo {
    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 角色ID
     */
    private Integer parentId;

    /**
     * 权限代码描述
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;
}
