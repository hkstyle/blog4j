package com.blog4j.article.api;

import cn.dev33.satoken.annotation.SaIgnore;
import com.blog4j.article.service.ArticleService;
import com.blog4j.common.model.Result;
import com.blog4j.common.vo.DeleteUserArticleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 20:59
 **/
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ApiArticleController {
    private final ArticleService articleService;

    @SaIgnore()
    @GetMapping("/list")
    public Result list() {
        //PageInfo<ArticleRespVo> pageInfo = articleService.getWebArticleList(articleListReqVo);
        return Result.ok();
    }

    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     * @return 删除成功
     */
    @PostMapping("/deleteUserArticle")
    public Result deleteUserArticle(@RequestBody @Valid DeleteUserArticleVo vo) {
        articleService.deleteUserArticle(vo);
        return Result.ok();
    }
}
