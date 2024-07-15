package com.blog4j.user.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/15 12:49
 **/
@Data
public class EditOrganizationReqVo {

    @NotBlank(message = "组织ID不能为空")
    private String organizationId;

    @NotBlank(message = "组织名称不能为空")
    private String organizationName;

    @NotBlank(message = "组织头像不能为空")
    private String organizationAvatar;


    private String slogan;

    @NotBlank(message = "容纳人数不能为空")
    private Integer capacity;
}
