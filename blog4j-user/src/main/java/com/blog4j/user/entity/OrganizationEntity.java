package com.blog4j.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 组织信息
 * @Create on : 2024/6/29 10:42
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_organization")
public class OrganizationEntity {
    /**
     * 组织ID
     */
    @TableId
    private String organizationId;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织头像
     */
    private String organizationAvatar;

    /**
     * 组织创建者ID
     */
    private String organizationCreater;

    /**
     * 组织创建者的名称
     */
    private String organizationCreaterName;

    /**
     * 组织创建者的头像
     */
    private String organizationCreaterAvatar;

    /**
     * 组织管理员ID
     */
    private String organizationAdmin;

    /**
     * 组织管理员名称
     */
    private String organizationAdminName;

    /**
     * 组织口号
     */
    private String slogan;

    /**
     * 组织最大容纳人数
     */
    private int capacity;

    /**
     * 是否已被删除
     */
    @TableLogic
    private int deleted;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 组织状态(1:正常  2::锁定)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<OrganizationEntity> children;
}
