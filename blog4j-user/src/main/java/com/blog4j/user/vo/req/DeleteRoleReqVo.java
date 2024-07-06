package com.blog4j.user.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 23:00
 **/
@Data
public class DeleteRoleReqVo {
    /**
     * 待删除的角色ID集合
     */
    private List<String> roleIds;
}
