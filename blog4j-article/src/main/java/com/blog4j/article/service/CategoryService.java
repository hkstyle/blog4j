package com.blog4j.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.vo.req.CategoryEditReqVo;
import com.blog4j.article.vo.req.CategoryListReqVo;
import com.blog4j.article.vo.req.CreateCategoryReqVo;
import com.blog4j.article.vo.req.SaveCategoryLabelReqVo;

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

    /**
     * 删除分类信息
     *
     * @param ids 待删除的分类ID列表
     */
    void deleteCategory(List<String> ids);

    /**
     * 创建分类信息
     *
     * @param reqVo 分类信息
     */
    void create(CreateCategoryReqVo reqVo);

    /**
     * 根据创建者ID查询分类列表
     *
     * @param userId 创建者ID
     * @return 分类列表
     */
    List<CategoryEntity> getInfoByCreaterId(String userId);

    /**
     * 编辑分类信息
     *
     * @param reqVo 分类信息
     */
    void edit(CategoryEditReqVo reqVo);

    /**
     * 保存分类下的标签信息
     *
     * @param reqVo 请求信息
     */
    void saveCategoryLabel(SaveCategoryLabelReqVo reqVo);
}
