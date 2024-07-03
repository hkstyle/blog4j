package com.blog4j.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.vo.req.CategoryListReqVo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/27 22:10
 **/
public interface CategoryService extends IService<CategoryEntity>  {
    /**
     * 获取文章分类信息列表
     *
     * @param reqVo 查询条件
     * @return 文章分类信息
     */
    List<CategoryEntity> listCategory(CategoryListReqVo reqVo);
}
