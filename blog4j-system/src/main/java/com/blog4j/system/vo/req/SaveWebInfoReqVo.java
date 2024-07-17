package com.blog4j.system.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 21:52
 **/
@Data
public class SaveWebInfoReqVo {
    @NotNull(message = "ID不能为空")
    private Integer id;

    /**
     * 网站名称
     */
    @NotBlank(message = "网站名称不能为空")
    private String webName;

    /**
     * 网站标题
     */
    @NotBlank(message = "网站标题不能为空")
    private String webTitle;

    /**
     * 网站背景图
     */
    @NotBlank(message = "网站背景图不能为空")
    private String backgroundImage;
}
