package com.blog4j.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 00:26
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_role_permission_rel")
public class RolePermissionRelEntity {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 角色ID
     */
    private String roleId;
}
