package com.blog4j.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 13:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleInfoVo {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色代码描述
     */
    private String roleCode;

    /**
     * 角色中文描述
     */
    private String roleDesc;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;
}
