package com.blog4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/20 08:41
 **/
@Data
@TableName("t_text_valid_rules")
public class TextValidRulesEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文本审核规则代码
     */
    private String textRuleCode;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 文本校验规则描述
     */
    private String textRuleDesc;

    /**
     * 大于等于多少分视为校验不合格
     */
    private Integer textRuleConfidence;
}
