package com.blog4j.article.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/28 13:12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListReqVo {
    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    private Integer pageNo;

    /**
     * 每页大小
     */
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 状态
     */
    private Integer status;
}
