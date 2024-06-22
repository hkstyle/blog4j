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
 * @Description : 用户角色信息
 * @Create on : 2024/6/22 14:40
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_blog4j_role")
public class RoleEntity {
    /**
     * 角色ID
     */
    @TableId
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
