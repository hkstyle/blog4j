package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.UserEntity;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:15
 **/
public interface UserService extends IService<UserEntity> {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfoVo getUserInfoByUserName(String userName);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfoVo getUserInfoByUserId(String userId);
}
