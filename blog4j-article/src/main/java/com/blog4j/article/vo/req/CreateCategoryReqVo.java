package com.blog4j.article.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/3 22:50
 **/
@Data
public class CreateCategoryReqVo {
    /**
     * 分类代码描述
     */
    @NotBlank(message = "分类代码不能为空")
    private String categoryCode;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    /**
     * 是否禁用 (1:是 0:否)
     */
    @NotNull(message = "分类状态不能为空")
    private Integer status;

    /**
     * 作用域
     */
    @NotNull(message = "作用域不能为空")
    private Integer scope;
}
