package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 分类状态枚举
 * @Create on : 2024/7/4 12:41
 **/
@Getter
@AllArgsConstructor
public enum CategoryStatusEnum {
    NORMAL(1, "正常"),
    DISABLE(2, "禁用"),
    ;

    private final int code;

    private final String desc;
}
