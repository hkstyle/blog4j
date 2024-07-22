package com.blog4j.article.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 21:00
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_category")
public class CategoryEntity {
    /**
     * 分类ID
     */
    @TableId
    private String categoryId;

    /**
     * 分类代码描述
     */
    private String categoryCode;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 是否禁用 (1:是 0:否)
     */
    private Integer status;

    /**
     * 创建人用户ID
     */
    private String creater;

    /**
     * 创建者名称
     */
    private String createrName;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;
}
