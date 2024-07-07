package com.blog4j.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 15:09
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserLastLoginTimeReqVo {
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 用户最近一次登录时间
     */
    @NotBlank(message = "用户最近一次登录时间不能为空")
    private String lastLoginTime;
}
