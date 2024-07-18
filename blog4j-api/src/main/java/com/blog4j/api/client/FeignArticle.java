package com.blog4j.api.client;

import com.blog4j.api.vo.DeleteUserArticleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 21:01
 **/
@FeignClient(value = "blog4j-article", path = "/feign")
public interface FeignArticle {
    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     */
    @PostMapping("/deleteUserArticle")
    void deleteUserArticle(@RequestBody DeleteUserArticleVo vo);
}
