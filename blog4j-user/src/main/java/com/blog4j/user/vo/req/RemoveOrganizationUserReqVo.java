package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 19:20
 **/
@Data
public class RemoveOrganizationUserReqVo {
    /**
     * 组织ID
     */
    @NotBlank(message = "组织ID不能为空")
    private String organizationId;

    /**
     * 移除的用户ID集合
     */
    @NotEmpty(message = "用户ID不能为空")
    private List<String> userIds;
}
