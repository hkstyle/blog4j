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
 * @Description : 标签信息
 * @Create on : 2024/7/2 21:14
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_label")
public class LabelEntity {
    /**
     * 标签ID
     */
    @TableId
    private String labelId;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 标签代码
     */
    private String labelCode;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;
}
