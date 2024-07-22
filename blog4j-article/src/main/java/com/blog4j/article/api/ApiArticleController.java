package com.blog4j.article.api;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.service.ArticleService;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.common.model.Result;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/21 21:31
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ApiArticleController {
    private final ArticleService articleService;

    /**
     * 获取文章列表
     *
     * @param articleListReqVo 查询条件
     * @return 文章列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody ArticleListReqVo articleListReqVo) {
        PageInfo<ArticleRespVo> pageInfo = articleService.getArticleList(articleListReqVo);
        return Result.ok(pageInfo);
    }

    /**
     * 根据文章ID获取文章信息
     *
     * @param articleId 文章ID
     * @return 文章信息
     */
    @GetMapping("/info/{articleId}")
    public Result info(@PathVariable("articleId") String articleId) {
        ArticleEntity article = articleService.getById(articleId);
        return Result.ok(article);
    }

    /**
     * 获取排行榜的文章
     *
     * @return 文章列表
     */
    @GetMapping("/getViewsArticle")
    public Result getWeekViewsArticle() {
        List<ArticleEntity> list = articleService.getViewsArticle();
        return Result.ok(list);
    }
}
