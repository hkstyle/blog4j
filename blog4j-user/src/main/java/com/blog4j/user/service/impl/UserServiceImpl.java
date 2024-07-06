package com.blog4j.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.UserStatusEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:16
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private static final String PASSWORD = "blog4j@test_123";

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserInfoVo getUserInfoByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            log.error("UserName is blank .");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUserName, userName);
        wrapper.ne(UserEntity::getStatus, UserStatusEnum.LOCK.getCode());
        UserEntity userEntity = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(userEntity)) {
            log.error("User is empty .");
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userEntity, userInfoVo);
        return userInfoVo;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public UserInfoVo getUserInfoByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            log.error("userId is blank .");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserId, userId);
        queryWrapper.ne(UserEntity::getStatus, UserStatusEnum.LOCK.getCode());
        UserEntity userEntity = this.baseMapper.selectOne(queryWrapper);
        if (Objects.isNull(userEntity)) {
            log.error("User is empty .");
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        RoleEntity roleEntity = roleMapper.selectById(userEntity.getRoleId());
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserId(userId)
                .setUserName(userEntity.getUserName())
                .setAvatar(userEntity.getAvatar())
                .setRoleCode(roleEntity.getRoleCode());
        return userInfoVo;
    }

    /**
     * 查询用户列表
     *
     * @param reqVo 查询条件
     * @return 用户列表
     */
    @Override
    public List<UserListRespVo> userList(UserListReqVo reqVo) {
        PageHelper.startPage(reqVo.getPageNo(), reqVo.getPageSize());
        return this.baseMapper.userList(reqVo.getUserName(), reqVo.getStatus());
    }

    /**
     * 创建用户信息
     *
     * @param reqVo 用户信息
     */
    @Override
    public void create(CreateUserReqVo reqVo) {
        this.beforeCreate(reqVo);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(reqVo, user);
        user.setUserId(IdGeneratorSnowflakeUtil.snowflakeId())
                .setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCreateTime(CommonUtil.getCurrentDateTime())
                .setPassword(PASSWORD)
                .setStatus(UserStatusEnum.NORMAL.getCode());
        this.baseMapper.insert(user);
    }

    private void beforeCreate(CreateUserReqVo reqVo) {
        String userName = reqVo.getUserName();
        String phone = reqVo.getPhone();
        String roleId = reqVo.getRoleId();

        // 用户名称不能重复
        LambdaQueryWrapper<UserEntity> wrapper1 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserName, userName);
        List<UserEntity> val1 = this.baseMapper.selectList(wrapper1);
        if (CollectionUtil.isNotEmpty(val1)) {
            throw new Blog4jException(ErrorEnum.USERNAME_REPEAT_ERROR);
        }

        // 用户手机号码不能重复
        if (StringUtils.isNotBlank(phone)) {
            LambdaQueryWrapper<UserEntity> wrapper2 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getPhone, phone);
            List<UserEntity> val2 = this.baseMapper.selectList(wrapper2);
            if (CollectionUtil.isNotEmpty(val2)) {
                throw new Blog4jException(ErrorEnum.PHONE_REPEAT_ERROR);
            }
        }

        RoleEntity role = roleMapper.selectById(roleId);
        if (Objects.isNull(role)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }
    }


}
