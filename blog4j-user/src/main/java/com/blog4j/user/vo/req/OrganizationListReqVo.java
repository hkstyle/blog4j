package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 13:00
 **/
@Data
public class OrganizationListReqVo {
    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    private Integer pageNo;

    /**
     * 每页大小
     */
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织状态
     */
    private Integer status;

    /**
     * 审批状态
     */
    private Integer approveStatus;

    /**
     * 查询类型(1:组织列表处 2:组织用户处  )
     */
    private Integer queryType;
}
