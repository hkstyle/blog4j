package com.blog4j.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 用户信息
 * @Create on : 2024/6/22 13:04
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@TableName("t_user")
public class UserEntity {
    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

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
     * 签名
     */
    private String sign;

    /**
     * 用户状态(1:正常 2:锁定)
     */
    private Integer status;

    /**
     * 性别(1:男  2:女 3:保密)
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户是否已删除(1:删除 0:未删除)
     */
    @TableLogic
    private int deleted;

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
}
