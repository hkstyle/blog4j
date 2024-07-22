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
    private String mdContent;

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
     * 审批状态(1:待审批  2:审批通过  3:审批拒绝)
     */
    private Integer approveStatus;

    /**
     * 审批人用户ID
     */
    private String approveUserId;

    /**
     * 审批人用户名称
     */
    private String approveUserName;

    /**
     * 审批时间
     */
    private String approveTime;

    /**
     * 审批留言
     */
    private String approveMessage;

    /**
     * 文章类型(1:原创  2:转载)
     */
    private Integer articleType;

    /**
     * 转载的链接
     */
    private String curationLink;

    /**
     * 摘要
     */
    private String summary;

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
    private Integer allowComment;

    /**
     * 是否允许下载
     */
    private Integer allowDownload;

    /**
     * 是否定时发布
     */
    private Integer timedRelease;

    /**
     * 浏览数
     */
    private Integer views;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 创建时间
     */
    private String createTime;
}
