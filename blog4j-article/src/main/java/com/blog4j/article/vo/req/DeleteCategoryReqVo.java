package com.blog4j.article.vo.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/3 22:37
 **/
@Data
public class DeleteCategoryReqVo {
    @NotEmpty(message = "分类ID列表不能为空")
    private List<String> ids;
}
