package com.blog4j.article.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/1 22:58
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEditReqVo {
    /**
     * 文章ID
     */
    @NotBlank(message = "文章ID不能为空")
    private String articleId;

    /**
     * 文章标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 文章分类ID
     */
    @NotBlank(message = "分类不能为空")
    private String categoryId;

    /**
     * 文章封面图
     */
    @NotBlank(message = "封面图不能为空")
    private String cover;

    /**
     * 文章公开类型
     */
    @NotNull(message = "公开类型不能为空")
    private Integer publicType;

    /**
     * 是否允许评论
     */
    @NotNull(message = "是否允许评论不能为空")
    private Integer allowComment;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String htmlContent;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String mdContent;

    /**
     * 文章类型
     */
    @NotNull(message = "文章类型不能为空")
    private Integer articleType;

    /**
     * 转载链接
     */
    private Integer curationLink;

    /**
     * 是否允许下载
     */
    @NotNull(message = "是否允许下载不能为空")
    private Integer allowDownload;

    /**
     * 摘要
     */
    @NotBlank(message = "摘要不能为空")
    private String summary;

    /**
     * 是否置顶
     */
    private Integer stick;
}
