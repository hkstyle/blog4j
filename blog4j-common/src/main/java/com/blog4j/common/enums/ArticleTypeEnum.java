package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/20 09:41
 **/
@Getter
@AllArgsConstructor
public enum ArticleTypeEnum {
    /**
     * 原创
     */
    ORIGINAL(1, "原创"),

    /**
     * 转载
     */
    TRANSPORT(2, "转载"),
    ;

    private final int code;
    private final String desc;
}
