package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 文章的状态枚举
 * @Create on : 2024/6/28 13:23
 **/
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {
    /**
     * 草稿中
     */
    DRAFT(1),

    /**
     * 待发布
     */
    WAIT(2),

    /**
     * 已发布
     */
    ONLINE(3),
        ;

    private final int code;
}
