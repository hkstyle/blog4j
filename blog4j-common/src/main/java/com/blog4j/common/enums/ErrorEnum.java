package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/20 12:29
 **/
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    SYSTEM_ERROR(100, "系统异常！"),
    IO_ERROR(101, "IO异常！"),

    INVALID_PARAMETER_ERROR(300, "参数非法！"),
    NO_PERMISSION_ERROR(403, "权限不足，请联系管理员！"),
    INVALID_REQUEST_ERROR(405, "无效请求！"),

    /**
     * 用户相关
     */
    USER_NOT_EXIST_ERROR(301, "用户不存在"),
    INVALID_CAPTCHA_ERROR(302, "验证码非法"),
    ROLE_INFO_EMPTY_ERROR(303, "角色信息为空"),
    PERMISSION_INFO_EMPTY_ERROR(304, "权限信息为空"),
    ORGANIZATION_INFO_EMPTY_ERROR(305, "组织信息为空"),
    USERNAME_REPEAT_ERROR(306, "用户名称重复"),
    PHONE_REPEAT_ERROR(307, "用户手机号码重复"),
    DELETE_ORGANIZATION_ADMIN_ERROR(308, "不能删除超级管理员和组织管理员"),
    DELETE_CURRENT_LOGIN_ERROR(309, "不能删除当前登录的用户"),
    ROLE_NAME_REPEAT_ERROR(310, "角色名称不能重复"),
    ROLE_CODE_REPEAT_ERROR(311, "角色代码不能重复"),
    ROLE_USED_ERROR(312, "角色被使用，不能删除"),
    PERMISSION_CODE_REPEAT_ERROR(313, "权限代码不能重复"),
    PERMISSION_NAME_REPEAT_ERROR(314, "权限名称不能重复"),
    PASSWORD_ERROR(315, "密码错误"),
    ORGANIZATION_LOCK_ERROR(316, "组织已被锁定"),
    ORGANIZATION_MAX_CAPACITY_ERROR(317, "组织成员超出上限"),
    ORGANIZATION_NAME_REPEAT_ERROR(318, "组织名称重复"),
    USER_LOCK_ERROR(319, "用户已被锁定"),
    USER_ORGANIZATION_MAX_ERROR(320, "用户创建组织的数量超出上限"),
    IMPORT_USER_MAX_COUNT_ERROR(321, "导入用户条数超出上限"),
    PHONE_ERROR(322, "手机号码不合法"),
    SEX_ERROR(323, "性别不合法"),
    EXPORT_USER_ERROR(324, "用户信息导出失败"),
    EXPORT_ORGANIZATION_ERROR(325, "组织信息导出失败"),
    ORGANIZATION_CAPACITY_MAX_ERROR(326, "组织容纳人数超出上限"),
    ORGANIZATION_CAPACITY_NULL_ERROR(327, "组织容纳人数不能为空"),
    ORGANIZATION_NAME_EMPTY_ERROR(328, "组织名称不能为空"),
    USER_NAME_EMPTY_ERROR(329, "用户名称不能为空"),
    USER_NAME_CHECK_ERROR(330, "用户名称校验失败"),
    EMAIL_ERROR(331, "邮箱不合法"),
    EMAIL_REPEAT_ERROR(332, "用户邮箱重复"),

    /**
     * OSS相关
     */
    UPLOAD_FILE_ERROR(501, "文件上传失败！"),
    FILE_MAX_ERROR(502, "文件太大，上传失败！"),


    /**
     * 文章相关
     */
    CATEGORY_INFO_EMPTY_ERROR(601, "分类信息为空"),
    ARTICLE_STATUS_ILLEGAL(602, "文章状态非法！"),
    CATEGORY_BIND_ARTICLE_ERROR(603, "该分类绑定了文章，不能删除！")
    ;

    private final int errorCode;

    private final String errorMsg;
}
