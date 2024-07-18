package com.blog4j.article.feign;

import com.blog4j.api.client.FeignArticle;
import com.blog4j.article.service.ArticleService;
import com.blog4j.api.vo.DeleteUserArticleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 21:15
 **/
@RestController
@RequestMapping("/feign")
@RequiredArgsConstructor
public class ArticleFeignController implements FeignArticle {
    private final ArticleService articleService;

    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     */
    @PostMapping("/deleteUserArticle")
    public void deleteUserArticle(@RequestBody @Valid DeleteUserArticleVo vo) {
        articleService.deleteUserArticle(vo);
    }
}
