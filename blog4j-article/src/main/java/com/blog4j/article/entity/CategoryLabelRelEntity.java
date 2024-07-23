package com.blog4j.article.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/23 12:37
 **/
@Data
@TableName("t_category_label_rel")
public class CategoryLabelRelEntity {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 标签ID
     */
    private String labelId;
}
