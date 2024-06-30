package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 文章的公开类型枚举
 * @Create on : 2024/6/28 13:23
 **/
@Getter
@AllArgsConstructor
public enum ArticlePublicTypeEnum {
    /**
     * 仅对自己可见
     */
    VISIBLE_SELF(1),

    /**
     * 组织内成员可见
     */
    VISIBLE_ORGANIZATION(2),

    /**
     * 所有人可见
     */
    VISIBLE_ALL(3),
        ;

    private final int code;
}
