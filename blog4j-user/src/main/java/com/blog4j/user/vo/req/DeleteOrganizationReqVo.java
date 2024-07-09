package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 18:40
 **/
@Data
public class DeleteOrganizationReqVo {
    /**
     * 组织ID集合
     */
    @NotEmpty(message = "组织ID不能为空")
    private List<String> organizationIds;
}
