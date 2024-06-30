package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 角色信息 访客完善用户信息成为普通用户
 * 普通用户提交申请成为创作者
 * 普通用户提交加入组织的神情成员某个组织的成员
 * @Create on : 2024/6/27 22:25
 **/
@Getter
@AllArgsConstructor
public enum RoleEnum {
    /**
     * 超级管理员, 平台级别管理员，拥有系统的所有操作权限
     */
    SUPER_ADMIN("SUPER_ADMIN"),

    /**
     * 组织管理员, 拥有该组织的所有操作权限
     */
    ORGANIZATION_ADMIN("ORGANIZATION_ADMIN"),

    /**
     * 创作者, 至少拥有该角色才能发表文章
     */
    COMPOSER("COMPOSER"),

    /**
     * 普通用户，不能发表文章，可以浏览首页被公开的文章，可以访问管理后台
     */
    ORDINARY("ORDINARY"),

    /**
     * 访客, 最基本的角色，不能发表文章，可以浏览首页被公开的文章，不能访问管理后台
     */
    VISITOR("VISITOR")
    ;

    private final String desc;

}
