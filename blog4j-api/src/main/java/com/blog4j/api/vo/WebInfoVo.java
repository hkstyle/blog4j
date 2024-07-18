package com.blog4j.api.vo;

import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 13:34
 **/
@Data
public class WebInfoVo {
    private Integer id;

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网站标题
     */
    private String webTitle;

    /**
     * 网站背景图
     */
    private String backgroundImage;
}
