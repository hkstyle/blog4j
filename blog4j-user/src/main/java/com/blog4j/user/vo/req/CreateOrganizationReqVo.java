package com.blog4j.user.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 20:18
 **/
@Data
public class CreateOrganizationReqVo {
    /**
     * 组织名称
     */
    @NotBlank(message = "组织名称不能为空")
    private String organizationName;

    /**
     * 组织头像
     */
    @NotBlank(message = "组织头像不能为空")
    private String organizationAvatar;

    /**
     * 组织口号
     */
    private String slogan;

    /**
     * 组织最大容纳人数
     */
    @NotNull(message = "组织容纳人数不能为空")
    @Range(min = 1, message = "组织容纳人数不能小于1")
    private Integer capacity;
}
