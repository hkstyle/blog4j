package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 分类作用域枚举
 * @Create on : 2024/7/4 12:41
 **/
@Getter
@AllArgsConstructor
public enum CategoryScopeEnum {
    ALL(1, "公共所有"),
    ORGANIZATION(2, "组织所有"),
    ;

    private final int code;

    private final String desc;
}
