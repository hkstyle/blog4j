package com.blog4j.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.UserStatusEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:16
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
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
        wrapper.ne(UserEntity::getStatus, UserStatusEnum.LOCK.getErrorCode());
        UserEntity userEntity = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(userEntity)) {
            log.error("User is empty .");
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userEntity, userInfoVo);
        return userInfoVo;
    }
}
