package com.blog4j.article.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 创建文章的上下文信息
 * @Create on : 2024/7/3 13:16
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateArticleContext {
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
     * 是否允许评论
     */
    private Integer allowComment;

    /**
     * 定时发布的时间
     */
    private String cronReleaseTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否定时发布
     */
    private Integer timedRelease;

    /**
     * 文章内容
     */
    private String htmlContent;

    /**
     * 当前登录用户ID
     */
    private String userId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 作者ID
     */
    private String authorId;
}
