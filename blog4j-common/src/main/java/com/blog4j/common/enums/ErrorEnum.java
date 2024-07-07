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

    INVALID_PARAMETER_ERROR(300, "参数非法！"),
    NO_PERMISSION_ERROR(403, "权限不足，请联系管理员！"),
    INVALID_REQUEST_ERROR(405, "无效请求！"),

    /**
     * 用户相关
     */
    USER_NOT_EXIST_ERROR(301, "用户不存在！"),
    INVALID_CAPTCHA_ERROR(302, "验证码非法！"),
    ROLE_INFO_EMPTY_ERROR(303, "角色信息为空！"),
    PERMISSION_INFO_EMPTY_ERROR(304, "权限信息为空！"),
    ORGANIZATION_INFO_EMPTY_ERROR(305, "组织信息为空！"),
    USERNAME_REPEAT_ERROR(306, "用户名称重复！"),
    PHONE_REPEAT_ERROR(307, "用户手机号码重复！"),
    DELETE_ORGANIZATION_ADMIN_ERROR(308, "不能删除超级管理员和组织管理员！"),
    DELETE_CURRENT_LOGIN_ERROR(309, "不能删除当前登录的用户！"),
    ROLE_NAME_REPEAT_ERROR(310, "角色名称不能重复！"),
    ROLE_CODE_REPEAT_ERROR(311, "角色代码不能重复！"),
    ROLE_USED_ERROR(312, "角色被使用，不能删除！"),
    PERMISSION_CODE_REPEAT_ERROR(313, "权限代码不能重复！"),
    PERMISSION_NAME_REPEAT_ERROR(314, "权限名称不能重复！"),
    PASSWORD_ERROR(315, "密码错误！"),

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
