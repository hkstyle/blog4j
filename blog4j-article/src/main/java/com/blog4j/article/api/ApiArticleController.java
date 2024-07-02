package com.blog4j.article.api;

import cn.dev33.satoken.annotation.SaIgnore;
import com.blog4j.article.service.ArticleService;
import com.blog4j.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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




    @SaIgnore()
    @GetMapping("/list")
    public Result list() {
        //PageInfo<ArticleRespVo> pageInfo = articleService.getWebArticleList(articleListReqVo);
        return Result.ok();
    }
}
