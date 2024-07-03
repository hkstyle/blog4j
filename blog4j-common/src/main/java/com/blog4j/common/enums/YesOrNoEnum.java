package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/3 13:35
 **/
@Getter
@AllArgsConstructor
public enum YesOrNoEnum {
    /**
     * 是
     */
    YES(1),

    /**
     * 否
     */
    NO(0),
    ;

    private final int code;
}
