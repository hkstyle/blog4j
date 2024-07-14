package com.blog4j.user.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 14:30
 **/
@Data
public class BatchCreateUserReqVo {
    /**
     * 用户信息集合
     */
    private List<CreateUserReqVo> list;
}
