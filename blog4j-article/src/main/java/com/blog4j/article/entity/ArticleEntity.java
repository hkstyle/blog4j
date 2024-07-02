package com.blog4j.article.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 文章信息
 * @Create on : 2024/6/28 12:53
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_article")
public class ArticleEntity {
    /**
     * 文章ID
     */
    @TableId
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

    /**
     * 文章状态(1:草稿 2:待发布 3:已发布)
     */
    private int status;

    /**
     * 文章公开类型(1:仅对自己公开  2:仅对组织内成员公开 3:所有人公开)
     */
    private int publicType;

    /**
     * 定时发布的时间
     */
    private String cronReleaseTime;

    /**
     * 发布人用户ID
     */
    private String publishUserId;

    /**
     * 是否允许评论
     */
    private int allowComment;

    /**
     * 是否定时发布
     */
    private int timedRelease;

    /**
     * 是否已被删除
     */
    @TableLogic
    private int deleted;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

}
