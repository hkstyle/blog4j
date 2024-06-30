package com.blog4j.common.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/29 11:01
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class OrganizationVo {
    /**
     * 组织ID
     */
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
}
