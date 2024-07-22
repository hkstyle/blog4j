package com.blog4j.article.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 创建文章信息的请求体
 * @Create on : 2024/7/3 13:14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleReqVo {
    /**
     * 文章标题
     */
    @NotBlank(message = "标题不能为空")
    @Size(min = 1, max = 50, message = "文章标题字数超出上限")
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
     * 是否定时发布
     */
    @NotNull(message = "是否定时发布不能为空")
    private Integer timedRelease;

    /**
     * 定时发布的时间
     */
    @Future(message = "发布时间不能小于当前时间")
    private String cronReleaseTime;

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
}
