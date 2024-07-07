package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.DeleteUserReqVo;
import com.blog4j.common.vo.EditUserLastLoginTimeReqVo;
import com.blog4j.user.vo.req.EditUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;

import java.util.List;

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

    /**
     * 查询用户列表
     *
     * @param reqVo 查询条件
     * @return 用户列表
     */
    List<UserListRespVo> userList(UserListReqVo reqVo);

    /**
     * 创建用户信息
     *
     * @param reqVo 用户信息
     */
    void create(CreateUserReqVo reqVo);

    /**
     * 编辑用户信息
     *
     * @param reqVo 用户信息
     */
    void edit(EditUserReqVo reqVo);

    /**
     * 删除用户
     *
     * @param reqVo 用户信息
     */
    void delete(DeleteUserReqVo reqVo);

    /**
     * 更新用户的最近一次登录时间
     *
     * @param reqVo 请求信息
     */
    void updateUserLastLoginTime(EditUserLastLoginTimeReqVo reqVo);
}
