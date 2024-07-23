package com.blog4j.article.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.entity.CategoryLabelRelEntity;
import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.mapper.CategoryLabelRelMapper;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.mapper.LabelMapper;
import com.blog4j.article.service.LabelService;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 21:18
 **/
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LabelServiceImpl extends ServiceImpl<LabelMapper, LabelEntity> implements LabelService {
    private final CategoryMapper categoryMapper;
    private final CategoryLabelRelMapper categoryLabelRelMapper;

    /**
     * 根据分类ID查询标签信息列表
     *
     * @param categoryId 分类ID
     * @return 标签信息列表
     */
    @Override
    public List<LabelEntity> getByCategoryId(String categoryId) {
        CategoryEntity category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            log.error("Category is null .");
            throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
        }

        LambdaQueryWrapper<CategoryLabelRelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryLabelRelEntity::getCategoryId, categoryId);
        List<CategoryLabelRelEntity> relList = categoryLabelRelMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(relList)) {
            return null;
        }

        Set<String> labelIdSet = relList.stream().map(CategoryLabelRelEntity::getLabelId).collect(Collectors.toSet());
        return this.baseMapper.selectBatchIds(labelIdSet);
    }

    /**
     * 根据创建者ID获取标签列表
     *
     * @param userId 创建者ID
     * @return 标签列表
     */
    @Override
    public List<LabelEntity> getListByCreaterId(String userId) {
        LambdaQueryWrapper<LabelEntity> wrapper = new LambdaQueryWrapper<LabelEntity>()
                .eq(LabelEntity::getCreater, userId);
        return this.baseMapper.selectList(wrapper);
    }
}
