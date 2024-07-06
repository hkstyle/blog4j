package com.blog4j.article.vo.req;

import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/3 20:57
 **/
@Data
public class CategoryListReqVo {
    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 作用域
     */
    private Integer scope;
}
