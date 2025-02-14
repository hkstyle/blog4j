package com.blog4j.user.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/4 22:33
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class UserListRespVo {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户状态(1:正常 2:锁定)
     */
    private Integer status;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户最后一次登录时间
     */
    private String lastLoginTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 组织ID
     */
    private String organizationId;

    /**
     * 组织名称
     */
    private String organizationName;
}
