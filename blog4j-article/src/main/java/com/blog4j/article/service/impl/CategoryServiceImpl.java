package com.blog4j.article.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.api.client.FeignUser;
import com.blog4j.api.vo.UserInfoVo;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.entity.CategoryLabelRelEntity;
import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.mapper.ArticleMapper;
import com.blog4j.article.mapper.CategoryLabelRelMapper;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.mapper.LabelMapper;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CategoryEditReqVo;
import com.blog4j.article.vo.req.CategoryListReqVo;
import com.blog4j.article.vo.req.CreateCategoryReqVo;
import com.blog4j.article.vo.req.SaveCategoryLabelReqVo;
import com.blog4j.common.enums.CategoryStatusEnum;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {
    private final ArticleMapper articleMapper;
    private final FeignUser feignUser;
    private final LabelMapper labelMapper;
    private final CategoryLabelRelMapper categoryLabelRelMapper;

    /**
     * 获取文章分类信息列表
     *
     * @param reqVo 查询条件
     * @return 文章分类信息
     */
    @Override
    public List<CategoryEntity> listCategory(CategoryListReqVo reqVo) {
        String categoryName = reqVo.getCategoryName();
        Integer status = reqVo.getStatus();
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(CategoryEntity::getCreateTime);
        if (StringUtils.isNotBlank(categoryName)) {
            wrapper.like(CategoryEntity::getCategoryName, categoryName);
        }
        if (Objects.nonNull(status)) {
            wrapper.eq(CategoryEntity::getStatus, status);
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
        Integer status = reqVo.getStatus();
        if (!Objects.equals(status, CategoryStatusEnum.DISABLE.getCode()) &&
            !Objects.equals(status, CategoryStatusEnum.NORMAL.getCode())) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        String userId = StpUtil.getLoginIdAsString();
        UserInfoVo user = feignUser.getUserInfoByUserId(userId);

        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(reqVo, category);
        category.setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCreateTime(CommonUtil.getCurrentDateTime())
                .setCategoryId(IdGeneratorSnowflakeUtil.snowflakeId())
                .setCreater(userId)
                .setCreaterName(user.getUserName())
                .setStatus(status);
        this.baseMapper.insert(category);
    }

    /**
     * 根据创建者ID查询分类列表
     *
     * @param userId 创建者ID
     * @return 分类列表
     */
    @Override
    public List<CategoryEntity> getInfoByCreaterId(String userId) {
        LambdaQueryWrapper<CategoryEntity> wrapper = new LambdaQueryWrapper<CategoryEntity>()
                .eq(CategoryEntity::getCreater, userId)
                .eq(CategoryEntity::getStatus, CategoryStatusEnum.NORMAL.getCode());
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 编辑分类信息
     *
     * @param reqVo 分类信息
     */
    @Override
    public void edit(CategoryEditReqVo reqVo) {
        String categoryId = reqVo.getCategoryId();
        CategoryEntity category = this.baseMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(reqVo, categoryEntity);
        categoryEntity.setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(categoryEntity);
    }

    /**
     * 保存分类下的标签信息
     *
     * @param reqVo 请求信息
     */
    @Override
    public void saveCategoryLabel(SaveCategoryLabelReqVo reqVo) {
        String categoryId = reqVo.getCategoryId();
        List<String> labelIds = reqVo.getLabelIds();

        CategoryEntity category = this.baseMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
        }

        List<LabelEntity> labelList = labelMapper.selectBatchIds(labelIds);
        if (CollectionUtil.size(labelIds) != CollectionUtil.size(labelList)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        // todo 性能优化
        for (LabelEntity label : labelList) {
            CategoryLabelRelEntity build = CategoryLabelRelEntity.builder()
                    .labelId(label.getLabelId())
                    .categoryId(categoryId)
                    .build();
            categoryLabelRelMapper.insert(build);
        }
    }
}
