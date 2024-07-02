package com.blog4j.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.vo.resp.LabelRespVo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 21:18
 **/
public interface LabelService extends IService<LabelEntity>  {
    /**
     * 根据分类ID查询标签信息列表
     *
     * @param categoryId 分类ID
     * @return 标签信息列表
     */
    List<LabelRespVo> getByCategoryId(String categoryId);
}
