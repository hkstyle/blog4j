package com.blog4j.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/27 22:10
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {
}
