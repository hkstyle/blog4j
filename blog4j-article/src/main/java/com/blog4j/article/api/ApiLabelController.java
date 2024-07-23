package com.blog4j.article.api;

import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.service.LabelService;
import com.blog4j.article.vo.resp.LabelRespVo;
import com.blog4j.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 21:19
 **/
@RestController
@RequestMapping("/api/label")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ApiLabelController {
    private final LabelService labelService;

    /**
     * 根据分类ID查询标签信息列表
     *
     * @param categoryId 分类ID
     * @return 标签信息列表
     */
    @GetMapping("/getByCategoryId/{categoryId}")
    public Result getByCategoryId(@PathVariable("categoryId") String categoryId) {
        List<LabelEntity> list = labelService.getByCategoryId(categoryId);
        return Result.ok(list);
    }

    /**
     * 根据创建者ID获取标签列表
     *
     * @param userId 创建者ID
     * @return 标签列表
     */
    @GetMapping("/getListByCreaterId/{userId}")
    public Result getListByCreaterId(@PathVariable("userId") String userId) {
        List<LabelEntity> list = labelService.getListByCreaterId(userId);
        return Result.ok(list);
    }
}
