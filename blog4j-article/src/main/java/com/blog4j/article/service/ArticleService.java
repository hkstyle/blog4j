package com.blog4j.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.article.context.CreateArticleContext;
import com.blog4j.article.context.UpdateArticleContext;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.article.vo.resp.ArticleStatusRespVo;
import com.blog4j.api.vo.DeleteUserArticleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/28 13:02
 **/
public interface ArticleService extends IService<ArticleEntity>  {
    /**
     * 获取文章列表
     *
     * @param articleListReqVo 查询条件
     * @return 文章列表
     */
    PageInfo<ArticleRespVo> getArticleList(ArticleListReqVo articleListReqVo);

    /**
     * 获取所有的文章状态
     *
     * @return 所有的文章状态
     */
    List<ArticleStatusRespVo> statusList();

    /**
     * 根据文章ID删除文章
     *
     * @param articleId 文章ID
     */
    void deleteArticle(String articleId);

    /**
     * 根据文章ID发布文章
     *
     * @param articleId 文章ID
     */
    void publishArticle(String articleId);

    /**
     * 查询文章详情信息
     *
     * @param articleId 文章ID
     * @return 文章详情信息
     */
    ArticleRespVo info(String articleId);

    /**
     * 编辑文章信息
     *
     * @param context 更新文章信息的上下文信息
     */
    void updateArticle(UpdateArticleContext context);

    /**
     * 创建文章信息
     *
     * @param context 创建文章信息的上下文信息
     */
    void create(CreateArticleContext context);

    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     */
    void deleteUserArticle(DeleteUserArticleVo vo);
}
