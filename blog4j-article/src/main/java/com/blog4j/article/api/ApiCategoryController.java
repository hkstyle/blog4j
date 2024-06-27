package com.blog4j.article.api;

import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.service.CategoryService;
import com.blog4j.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/27 22:11
 **/
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取文章分类信息列表
     *
     * @return 文章分类信息
     */
    @GetMapping("/list")
    public Result list() {
        List<CategoryEntity> list = categoryService.list();
        return Result.ok(list);
    }
}
