package com.blog4j.user.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 14:36
 **/
@Data
public class EditUserReqVo {
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 角色ID
     */
    @NotBlank(message = "角色ID不能为空")
    private String roleId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 用户头像
     */
    @NotBlank(message = "用户头像不能为空")
    private String avatar;

    /**
     * 邮箱
     */
    @Email(message = "邮箱不合法")
    private String email;

    /**
     * 状态
     */
    @Range(min = 1, max = 2)
    private Integer status;

    /**
     * 地址
     */
    private String address;

    /**
     * 性别
     */
    private Integer sex;
}
