package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 组织审批状态枚举
 * @Create on : 2024/7/10 13:15
 **/
@Getter
@AllArgsConstructor
public enum OrganizationApproveStatus {
    /**
     * 待审批
     */
    WAIT_APPROVE(1, "待审批"),

    /**
     * 审批通过
     */
    PASS(2, "审批通过"),

    /**
     * 审批拒绝
     */
    REJECT(3, "审批拒绝"),
    ;

    private final int code;
    private final String desc;
}
