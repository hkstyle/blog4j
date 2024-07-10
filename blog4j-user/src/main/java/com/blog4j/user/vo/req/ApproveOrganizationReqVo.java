package com.blog4j.user.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/10 22:01
 **/
@Data
public class ApproveOrganizationReqVo {
    /**
     * 组织ID
     */
    @NotBlank(message = "组织ID不能为空")
    private String organizationId;

    /**
     * 审批状态
     */
    @Range(min = 1, max = 3, message = "审批状态非法！")
    @NotNull(message = "审批状态不能为空")
    private Integer approveStatus;

    /**
     * 审批留言
     */
    private String approveMessage;
}
