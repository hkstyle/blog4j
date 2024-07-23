package com.blog4j.article.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/23 13:22
 **/
@Data
public class SaveCategoryLabelReqVo {
    @NotBlank(message = "分类ID不能为空")
    private String categoryId;

    @NotEmpty(message = "标签列表不能为空")
    private List<String> labelIds;
}
