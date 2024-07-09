package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 组织的状态枚举
 * @Create on : 2024/6/28 13:23
 **/
@Getter
@AllArgsConstructor
public enum OrganizationStatusEnum {
    /**
     * 正常
     */
    NORMAL(1),

    /**
     * 锁定
     */
    LOCK(2),

        ;

    private final int code;
}
