package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/22 13:23
 **/
@Getter
@AllArgsConstructor
public enum ApproveEnum {
    /**
     * 待审批
     */
    WAIT_APPROVE(1),

    /**
     * 审批通过
     */
    PASS(2),

    /**
     * 审批拒绝
     */
    REJECT(3),
    ;


    private final Integer code;
}
