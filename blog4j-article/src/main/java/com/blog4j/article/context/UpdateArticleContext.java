package com.blog4j.article.context;

import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 12:30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleContext {
    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章分类ID
     */
    private String categoryId;

    /**
     * 文章封面图
     */
    private String cover;

    /**
     * 文章公开类型
     */
    private Integer publicType;

    /**
     * 文章内容
     */
    private String htmlContent;

    /**
     * 文章分类信息
     */
    private CategoryEntity category;

    /**
     * 当前文章ID对应的文章信息
     */
    private ArticleEntity article;
}
