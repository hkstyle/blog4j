package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 16:06
 **/
@Data
public class ExportUserReqVo {
    /**
     * 用户id集合
     */
    @NotEmpty(message = "用户ID不能为空")
    private List<String> userIds;
}
