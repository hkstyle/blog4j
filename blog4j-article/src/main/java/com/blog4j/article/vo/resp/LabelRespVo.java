package com.blog4j.article.vo.resp;

import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 21:21
 **/
@Data
public class LabelRespVo {
    /**
     * 标签ID
     */
    private String labelId;

    /**
     * 标签代码
     */
    private String labelCode;

    /**
     * 标签名称
     */
    private String labelName;

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
