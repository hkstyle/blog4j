package com.blog4j.article.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CreateCategoryReqVo;
import com.blog4j.article.vo.req.DeleteCategoryReqVo;
import com.blog4j.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 分类
 * @Create on : 2024/7/3 22:13
 **/
@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 删除分类信息
     *
     * @param reqVo 待删除的分类ID列表
     * @return 删除成功
     */
    @SaCheckPermission(value = "CATEGORY:DELETE")
    @PostMapping ("/delete")
    public Result delete(@RequestBody DeleteCategoryReqVo reqVo) {
        categoryService.deleteCategory(reqVo.getIds());
        return Result.ok();
    }

    /**
     * 创建分类信息
     *
     * @param reqVo 分类信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "CATEGORY:ADD")
    @PostMapping("/create")
    public Result create(@RequestBody CreateCategoryReqVo reqVo) {
        categoryService.create(reqVo);
        return Result.ok();
    }
}
