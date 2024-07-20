package com.blog4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/20 08:39
 **/
@Data
@TableName("t_img_valid_rules")
public class ImgValidRulesEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片审核规则代码
     */
    private String imgRuleCode;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 图片校验规则描述
     */
    private String imgRuleDesc;

    /**
     * 大于等于多少分视为校验不合格
     */
    private Integer imgRuleConfidence;
}
