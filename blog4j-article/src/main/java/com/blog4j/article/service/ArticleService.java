package com.blog4j.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.article.vo.resp.ArticleStatusRespVo;
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
}
