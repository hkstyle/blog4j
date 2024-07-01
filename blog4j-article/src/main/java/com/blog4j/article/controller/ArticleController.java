package com.blog4j.article.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog4j.article.service.ArticleService;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.article.vo.resp.ArticleStatusRespVo;
import com.blog4j.common.model.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
