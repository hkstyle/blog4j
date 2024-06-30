package com.blog4j.article.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/30 17:32
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ArticleRespVo {
    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 文章分类ID
     */
    private String categoryId;

    /**
     * 文章分类名称
     */
    private String categoryName;

    /**
     * 文章封面图
     */
    private String cover;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * html内容
     */
    private String htmlContent;

    /**
     * 文章的作者用户ID
     */
    private String authorId;

    /**
     * 文章的作者名称
     */
    private String authorName;
}
