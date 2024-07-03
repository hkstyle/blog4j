package com.blog4j.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.mapper.ArticleMapper;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CategoryListReqVo;
import com.blog4j.article.vo.req.CreateCategoryReqVo;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/27 22:10
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {
    @Autowired
    private ArticleMapper articleMapper;

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
        wrapper.orderByAsc(CategoryEntity::getStatus);
        if (StringUtils.isNotBlank(categoryName)) {
            wrapper.like(CategoryEntity::getCategoryName, categoryName);
        }
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 删除分类信息
     *
     * @param ids 待删除的分类ID列表
     */
    @Override
    public void deleteCategory(List<String> ids) {
        if (ids.isEmpty()) {
            return;
        }

        ids.forEach(id -> {
            CategoryEntity category = this.baseMapper.selectById(id);
            if (Objects.isNull(category)) {
                throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
            }

            LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ArticleEntity::getCategoryId, id);
            List<ArticleEntity> articleList = articleMapper.selectList(wrapper);
            if (!articleList.isEmpty()) {
                throw new Blog4jException(ErrorEnum.CATEGORY_BIND_ARTICLE_ERROR);
            }
        });

        this.baseMapper.deleteBatchIds(ids);

        // TODO 删除该分类下的标签
    }

    /**
     * 创建分类信息
     *
     * @param reqVo 分类信息
     */
    @Override
    public void create(CreateCategoryReqVo reqVo) {
        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(reqVo, category);
        category.setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCreateTime(CommonUtil.getCurrentDateTime())
                .setCategoryId(IdGeneratorSnowflakeUtil.snowflakeId());
        this.baseMapper.insert(category);
    }
}
