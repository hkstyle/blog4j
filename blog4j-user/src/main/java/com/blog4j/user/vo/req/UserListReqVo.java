package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/4 22:31
 **/
@Data
public class UserListReqVo {
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
     * 用户名
     */
    private String userName;

    /**
     * 状态
     */
    private Integer status;
}
