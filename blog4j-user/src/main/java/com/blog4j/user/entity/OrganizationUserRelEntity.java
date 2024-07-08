package com.blog4j.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Description : 组织与用户的关系信息
 * @Create on : 2024/6/30 16:45
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_organization_user_rel")
public class OrganizationUserRelEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组织ID
     */
    private String organizationId;
}
