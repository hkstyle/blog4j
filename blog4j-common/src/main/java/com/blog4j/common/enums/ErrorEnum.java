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
    INVALID_PARAMETER_ERROR(300, "参数非法！"),
    NO_PERMISSION_ERROR(403, "权限不足，请联系管理员！"),
    INVALID_REQUEST_ERROR(405, "无效请求！"),

    /**
     * 用户相关
     */
    USER_NOT_EXIST_ERROR(301, "用户不存在！"),
    INVALID_CAPTCHA_ERROR(302, "验证码非法！"),
    ;

    private final int errorCode;

    private final String errorMsg;
}
