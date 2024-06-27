package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 用户状态枚举
 * @Create on : 2024/6/22 13:44
 **/
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    LOCK(2, "锁定"),
    NORMAL(1, "正常"),
    ;

    private final int code;

    private final String desc;
}
