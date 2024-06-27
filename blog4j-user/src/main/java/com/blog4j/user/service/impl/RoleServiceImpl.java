package com.blog4j.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.vo.RoleInfoVo;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 13:22
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @Override
    public RoleInfoVo getRoleInfoByUserId(String userId) {
        RoleEntity roleEntity = this.baseMapper.selectById(userId);
        if (Objects.isNull(roleEntity)) {
            log.error("Role info is null .");
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        RoleInfoVo roleInfoVo = new RoleInfoVo();
        BeanUtils.copyProperties(roleEntity, roleInfoVo);
        return roleInfoVo;
    }
}
