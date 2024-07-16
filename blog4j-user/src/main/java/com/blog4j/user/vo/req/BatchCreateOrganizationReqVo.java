package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/15 22:37
 **/
@Data
public class BatchCreateOrganizationReqVo {
    @NotEmpty(message = "组织信息不能为空")
    private List<CreateOrganizationReqVo> list;
}
