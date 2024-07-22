package com.blog4j.article.api;

import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CategoryListReqVo;
import com.blog4j.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequiredArgsConstructor
public class ApiCategoryController {
    private final CategoryService categoryService;

    /**
     * 获取文章分类信息列表
     *
     * @param reqVo 查询条件
     * @return 文章分类信息
     */
    @PostMapping("/list")
    public Result list(@RequestBody CategoryListReqVo reqVo) {
        List<CategoryEntity> list = categoryService.listCategory(reqVo);
        return Result.ok(list);
    }

    /**
     * 根据分类ID查询分类信息
     *
     * @param categoryId 分类ID
     * @return 分类信息
     */
    @GetMapping("/info/{categoryId}")
    public Result info(@PathVariable("categoryId") String categoryId) {
        CategoryEntity category = categoryService.getById(categoryId);
        return Result.ok(category);
    }

    /**
     * 根据创建者ID查询分类列表
     *
     * @param userId 创建者ID
     * @return 分类列表
     */
    @GetMapping("/getInfoByCreaterId/{userId}")
    public Result getInfoByCreaterId(@PathVariable("userId") String userId) {
        List<CategoryEntity> category = categoryService.getInfoByCreaterId(userId);
        return Result.ok(category);
    }
}
