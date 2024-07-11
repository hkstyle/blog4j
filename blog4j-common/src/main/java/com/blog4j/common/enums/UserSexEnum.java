package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 用户性别枚举
 * @Create on : 2024/6/28 13:23
 **/
@Getter
@AllArgsConstructor
public enum UserSexEnum {
    /**
     * 男
     */
    MAN(1, "男"),

    /**
     * 女
     */
    WOMAN(2, "女"),

    /**
     * 保密
     */
    SECRET(3, "保密"),
        ;

    private final int code;

    private final String desc;
}
