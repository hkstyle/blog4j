package com.blog4j.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.vo.req.ArticleListReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/28 13:02
 **/
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity>  {
    /**
     * 获取普通用户的文章列表
     *
     * @param idSet 组织用户ID
     * @param status 状态
     * @param categoryId 文章分类ID
     * @param title 标题
     * @return 文章列表
     */
    List<ArticleEntity> getOrdinaryArticleList(@Param("idSet") HashSet<String> idSet,
                                               @Param("status") Integer status,
                                               @Param("categoryId") String categoryId,
                                               @Param("title") String title);

    /**
     * 获取创作者的文章列表，且该创作者没有加入任何组织
     *
     * @param status 状态
     * @param categoryId 文章分类ID
     * @param title 标题
     * @param userId 用户ID
     * @return 文章列表
     */
    List<ArticleEntity> getComposerArticleList1(@Param("status") Integer status,
                                                @Param("categoryId") String categoryId,
                                                @Param("title") String title,
                                                @Param("userId") String userId);

    /**
     * 获取创作者的文章列表，且该创作者加入了组织
     *
     * @param idSet 组织用户ID
     * @param status 状态
     * @param categoryId 文章分类ID
     * @param title 标题
     * @param userId 用户ID
     * @return 文章列表
     */
    List<ArticleEntity> getComposerArticleList2(@Param("idSet") HashSet<String> idSet,
                                                @Param("status") Integer status,
                                                @Param("categoryId") String categoryId,
                                                @Param("title") String title,
                                                @Param("userId") String userId);
}
