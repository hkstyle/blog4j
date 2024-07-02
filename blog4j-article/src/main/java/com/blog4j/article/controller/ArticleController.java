package com.blog4j.article.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.blog4j.article.context.UpdateArticleContext;
import com.blog4j.article.service.ArticleService;
import com.blog4j.article.vo.req.ArticleEditReqVo;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.article.vo.resp.ArticleStatusRespVo;
import com.blog4j.common.model.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/1 12:28
 **/
@RestController
@RequestMapping("/")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param articleListReqVo 查询条件
     * @return 文章列表
     */
    @SaCheckLogin
    @PostMapping("/list")
    public Result list(@RequestBody ArticleListReqVo articleListReqVo) {
        PageInfo<ArticleRespVo> pageInfo = articleService.getArticleList(articleListReqVo);
        return Result.ok(pageInfo);
    }

    /**
     * 获取所有的文章状态
     *
     * @return 所有的文章状态
     */
    @SaCheckLogin
    @GetMapping("/status")
    public Result status() {
        List<ArticleStatusRespVo> list = articleService.statusList();
        return Result.ok(list);
    }

    /**
     * 根据文章ID删除文章
     *
     * @param articleId 文章ID
     * @return 统一响应体
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER"}, mode = SaMode.OR)
    @DeleteMapping("/delete/{articleId}")
    public Result delete(@PathVariable("articleId") String articleId) {
        articleService.deleteArticle(articleId);
        return Result.ok();
    }

    /**
     * 根据文章ID发布文章
     *
     * @param articleId 文章ID
     * @return 统一响应体
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER"}, mode = SaMode.OR)
    @PutMapping("/publish/{articleId}")
    public Result publish(@PathVariable("articleId") String articleId) {
        articleService.publishArticle(articleId);
        return Result.ok();
    }

    /**
     * 查询文章详情信息
     *
     * @param articleId 文章ID
     * @return 文章详情信息
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER", "ORDINARY"}, mode = SaMode.OR)
    @GetMapping("/info/{articleId}")
    public Result info(@PathVariable("articleId") String articleId) {
        ArticleRespVo articleRespVo = articleService.info(articleId);
        return Result.ok(articleRespVo);
    }

    /**
     * 编辑文章信息
     *
     * @param reqVo 文章信息
     * @return 统一响应体
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER"}, mode = SaMode.OR)
    @PutMapping("/update")
    public Result update(@RequestBody ArticleEditReqVo reqVo) {
        UpdateArticleContext context = new UpdateArticleContext();
        BeanUtils.copyProperties(reqVo, context);
        articleService.updateArticle(context);
        return Result.ok();
    }
}
