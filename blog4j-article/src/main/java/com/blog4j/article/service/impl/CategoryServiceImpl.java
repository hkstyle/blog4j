package com.blog4j.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CategoryListReqVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/27 22:10
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {
    /**
     * 获取文章分类信息列表
     *
     * @param reqVo 查询条件
     * @return 文章分类信息
     */
    @Override
    public List<CategoryEntity> listCategory(CategoryListReqVo reqVo) {
        String categoryName = reqVo.getCategoryName();
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(categoryName)) {
            wrapper.like(CategoryEntity::getCategoryName, categoryName);
        }
        return this.baseMapper.selectList(wrapper);
    }
}
