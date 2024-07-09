package com.blog4j.user.vo.req;

import lombok.Data;

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
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织状态
     */
    private Integer status;
}
