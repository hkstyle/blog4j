package com.blog4j.article.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.article.service.CategoryService;
import com.blog4j.article.vo.req.CategoryEditReqVo;
import com.blog4j.article.vo.req.CreateCategoryReqVo;
import com.blog4j.article.vo.req.DeleteCategoryReqVo;
import com.blog4j.article.vo.req.SaveCategoryLabelReqVo;
import com.blog4j.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 分类
 * @Create on : 2024/7/3 22:13
 **/
@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 删除分类信息
     *
     * @param reqVo 待删除的分类ID列表
     * @return 删除成功
     */
    @SaCheckPermission(value = "CATEGORY:DELETE")
    @PostMapping ("/delete")
    public Result delete(@RequestBody @Valid DeleteCategoryReqVo reqVo) {
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
    public Result create(@RequestBody @Valid CreateCategoryReqVo reqVo) {
        categoryService.create(reqVo);
        return Result.ok();
    }

    /**
     * 编辑分类信息
     *
     * @param reqVo 分类信息
     * @return 编辑成功
     */
    @PostMapping("/edit")
    public Result edit(@RequestBody @Valid CategoryEditReqVo reqVo) {
        categoryService.edit(reqVo);
        return Result.ok();
    }

    /**
     * 保存分类下的标签信息
     *
     * @param reqVo 请求信息
     * @return 保存成功
     */
    @PostMapping("/saveCategoryLabel")
    public Result saveCategoryLabel(@RequestBody @Valid SaveCategoryLabelReqVo reqVo) {
        categoryService.saveCategoryLabel(reqVo);
        return Result.ok();
    }
}
