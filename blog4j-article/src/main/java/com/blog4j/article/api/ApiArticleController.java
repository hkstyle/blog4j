package com.blog4j.article.api;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog4j.article.service.ArticleService;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.common.model.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 20:59
 **/
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {
    @Autowired
    private ArticleService articleService;

    @SaCheckLogin
    @PostMapping("/list")
    public Result list(@RequestBody ArticleListReqVo articleListReqVo) {
        PageInfo<ArticleRespVo> pageInfo = articleService.getArticleList(articleListReqVo);
        return Result.ok(pageInfo);
    }
}
