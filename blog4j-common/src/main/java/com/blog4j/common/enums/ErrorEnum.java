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

    NO_PERMISSION(403, "权限不足，请联系管理员！"),
    ;

    private final int errorCode;

    private final String errorMsg;
}
