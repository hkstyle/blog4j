package com.blog4j.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.RoleEnum;
import com.blog4j.common.enums.UserStatusEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.common.utils.RsaUtil;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.DeleteUserReqVo;
import com.blog4j.common.vo.EditUserLastLoginTimeReqVo;
import com.blog4j.user.vo.req.EditUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
                .setRoleCode(roleEntity.getRoleCode())
                .setPhone(userEntity.getPhone())
                .setRoleId(userEntity.getRoleId());
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
                .setPassword(RsaUtil.encrypt(PASSWORD))
                .setStatus(UserStatusEnum.NORMAL.getCode());
        this.baseMapper.insert(user);
    }

    /**
     * 编辑用户信息
     *
     * @param reqVo 用户信息
     */
    @Override
    public void edit(EditUserReqVo reqVo) {
        UserEntity user = this.beforeUpdate(reqVo);
        BeanUtils.copyProperties(reqVo, user);
        user.setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(user);
    }

    /**
     * 删除用户
     *
     * @param reqVo 用户信息
     */
    @Override
    public void delete(DeleteUserReqVo reqVo) {
        this.beforeDelete(reqVo);
        // TODO 删除用户名下的文章

        // TODO 删除用户名下文章的评论

        this.baseMapper.deleteBatchIds(reqVo.getUserIds());
    }

    /**
     * 更新用户的最近一次登录时间
     *
     * @param reqVo 请求信息
     */
    @Override
    public void updateUserLastLoginTime(EditUserLastLoginTimeReqVo reqVo) {
        String userId = reqVo.getUserId();
        String lastLoginTime = reqVo.getLastLoginTime();
        UserEntity user = this.baseMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        user.setLastLoginTime(lastLoginTime);
        this.baseMapper.updateById(user);
    }

    private void beforeDelete(DeleteUserReqVo reqVo) {
        String currentLoginUserId = StpUtil.getLoginIdAsString();
        List<String> userIds = reqVo.getUserIds();
        if (userIds.contains(currentLoginUserId)) {
            throw new Blog4jException(ErrorEnum.DELETE_CURRENT_LOGIN_ERROR);
        }
        List<UserEntity> userList = userIds.stream().map(userId -> {
            UserEntity user = this.baseMapper.selectById(userId);
            if (Objects.isNull(user)) {
                throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
            }
            return user;
        }).collect(Collectors.toList());

        // 不能删除超级管理员和组织管理员
        Set<String> roleIdSet = userList.stream().map(UserEntity::getRoleId).collect(Collectors.toSet());
        List<RoleEntity> roleList = roleMapper.selectBatchIds(roleIdSet);
        if (CollectionUtil.isNotEmpty(roleList)) {
            List<RoleEntity> list = roleList.stream().filter(role -> StringUtils.equals(role.getRoleCode(), RoleEnum.ORGANIZATION_ADMIN.getDesc()) ||
                    StringUtils.equals(role.getRoleCode(), RoleEnum.SUPER_ADMIN.getDesc())).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(list)) {
                throw new Blog4jException(ErrorEnum.DELETE_ORGANIZATION_ADMIN_ERROR);
            }
        }


    }

    private UserEntity beforeUpdate(EditUserReqVo reqVo) {
        // 用户ID是否存在
        String userId = reqVo.getUserId();
        UserEntity user = this.baseMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        // 用户名称不能重复
        LambdaQueryWrapper<UserEntity> wrapper1 = new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUserName, reqVo.getUserName())
                .ne(UserEntity::getUserId, userId);
        UserEntity val1 = this.baseMapper.selectOne(wrapper1);
        if (Objects.nonNull(val1)) {
            throw new Blog4jException(ErrorEnum.USERNAME_REPEAT_ERROR);
        }

        // 用户手机号码不能重复
        if (StringUtils.isNotBlank(reqVo.getPhone())) {
            LambdaQueryWrapper<UserEntity> wrapper2 = new LambdaQueryWrapper<UserEntity>()
                    .eq(UserEntity::getPhone, reqVo.getPhone())
                    .ne(UserEntity::getUserId, userId);
            UserEntity val2 = this.baseMapper.selectOne(wrapper2);
            if (Objects.nonNull(val2)) {
                throw new Blog4jException(ErrorEnum.PHONE_REPEAT_ERROR);
            }
        }

        // 角色是否合法
        RoleEntity role = roleMapper.selectById(reqVo.getRoleId());
        if (Objects.isNull(role)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }
        return user;
    }

    private void beforeCreate(CreateUserReqVo reqVo) {
        // 用户名称不能重复
        LambdaQueryWrapper<UserEntity> wrapper1 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserName, reqVo.getUserName());
        List<UserEntity> val1 = this.baseMapper.selectList(wrapper1);
        if (CollectionUtil.isNotEmpty(val1)) {
            throw new Blog4jException(ErrorEnum.USERNAME_REPEAT_ERROR);
        }

        // 用户手机号码不能重复
        if (StringUtils.isNotBlank(reqVo.getPhone())) {
            LambdaQueryWrapper<UserEntity> wrapper2 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getPhone, reqVo.getPhone());
            List<UserEntity> val2 = this.baseMapper.selectList(wrapper2);
            if (CollectionUtil.isNotEmpty(val2)) {
                throw new Blog4jException(ErrorEnum.PHONE_REPEAT_ERROR);
            }
        }

        RoleEntity role = roleMapper.selectById(reqVo.getRoleId());
        if (Objects.isNull(role)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }
    }


}
