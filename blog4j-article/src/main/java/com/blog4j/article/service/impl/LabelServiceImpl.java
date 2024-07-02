package com.blog4j.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.mapper.LabelMapper;
import com.blog4j.article.service.LabelService;
import com.blog4j.article.vo.resp.LabelRespVo;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 21:18
 **/
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, LabelEntity> implements LabelService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据分类ID查询标签信息列表
     *
     * @param categoryId 分类ID
     * @return 标签信息列表
     */
    @Override
    public List<LabelRespVo> getByCategoryId(String categoryId) {
        CategoryEntity category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            log.error("Category is null .");
            throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
        }

        LambdaQueryWrapper<LabelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LabelEntity::getCategoryId, categoryId);
        List<LabelEntity> labelList = this.baseMapper.selectList(wrapper);
        if (!labelList.isEmpty()) {
            return labelList.stream().map(item -> {
                LabelRespVo vo = new LabelRespVo();
                BeanUtils.copyProperties(item, vo);
                return vo;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
