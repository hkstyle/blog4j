package com.blog4j.user.feign;

import com.blog4j.common.model.FResult;
import com.blog4j.common.vo.DeleteUserArticleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 12:49
 **/
@FeignClient("blog4j-article")
public interface ArticleFeignService {
    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     * @return 删除成功
     */
    @PostMapping("/api/article/deleteUserArticle")
    FResult deleteUserArticle(@RequestBody DeleteUserArticleVo vo);
}
