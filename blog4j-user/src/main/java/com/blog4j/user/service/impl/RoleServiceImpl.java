package com.blog4j.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.api.vo.RoleInfoVo;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.service.RoleService;
import com.blog4j.user.vo.req.CreateRoleReqVo;
import com.blog4j.user.vo.req.DeleteRoleReqVo;
import com.blog4j.user.vo.req.EditRoleReqVo;
import com.blog4j.user.vo.req.RoleListReqVo;
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
 * @Create on : 2024/6/26 13:22
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @Override
    public RoleInfoVo getRoleInfoByUserId(String userId) {
        UserEntity user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        RoleEntity roleEntity = this.baseMapper.selectById(user.getRoleId());
        if (Objects.isNull(roleEntity)) {
            log.error("Role info is null .");
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        RoleInfoVo roleInfoVo = new RoleInfoVo();
        BeanUtils.copyProperties(roleEntity, roleInfoVo);
        return roleInfoVo;
    }

    /**
     * 获取所有的角色信息
     *
     * @param reqVo 查询条件
     * @return 角色信息
     */
    @Override
    public List<RoleEntity> roleList(RoleListReqVo reqVo) {
        String roleName = reqVo.getRoleName();
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(roleName)) {
            wrapper.like(RoleEntity::getRoleName, roleName);
        }
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 创建角色信息
     *
     * @param reqVo 角色信息
     */
    @Override
    public void create(CreateRoleReqVo reqVo) {
        this.beforeCreate(reqVo);
        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(reqVo, role);
        role.setCreateTime(CommonUtil.getCurrentDateTime())
                .setUpdateTime(CommonUtil.getCurrentDateTime())
                .setRoleId(IdGeneratorSnowflakeUtil.snowflakeId());
        this.baseMapper.insert(role);
    }

    /**
     * 编辑角色信息
     *
     * @param reqVo 角色信息
     */
    @Override
    public void edit(EditRoleReqVo reqVo) {
        this.beforeEdit(reqVo);
        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(reqVo, role);
        role.setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param reqVo 角色信息
     */
    @Override
    public void delete(DeleteRoleReqVo reqVo) {
        this.beforeDelete(reqVo);
        this.baseMapper.deleteBatchIds(reqVo.getRoleIds());
    }

    private void beforeDelete(DeleteRoleReqVo reqVo) {
        List<String> roleIds = reqVo.getRoleIds();
        List<RoleEntity> roleList = this.baseMapper.selectBatchIds(roleIds);
        if (CollectionUtil.size(roleList) != CollectionUtil.size(roleIds)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                .in(UserEntity::getRoleId, roleIds);
        Integer count = userMapper.selectCount(wrapper);
        if (count > 0) {
            throw new Blog4jException(ErrorEnum.ROLE_USED_ERROR);
        }
    }

    private void beforeEdit(EditRoleReqVo reqVo) {
        String roleName = reqVo.getRoleName();
        String roleCode = reqVo.getRoleCode();
        String roleId = reqVo.getRoleId();
        LambdaQueryWrapper<RoleEntity> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(RoleEntity::getRoleName, roleName);
        wrapper1.ne(RoleEntity::getRoleId, roleId);
        RoleEntity val1 = this.baseMapper.selectOne(wrapper1);
        if (Objects.nonNull(val1)) {
            throw new Blog4jException(ErrorEnum.ROLE_NAME_REPEAT_ERROR);
        }

        LambdaQueryWrapper<RoleEntity> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(RoleEntity::getRoleCode, roleCode);
        wrapper2.ne(RoleEntity::getRoleId, roleId);
        RoleEntity val2 = this.baseMapper.selectOne(wrapper2);
        if (Objects.nonNull(val2)) {
            throw new Blog4jException(ErrorEnum.ROLE_CODE_REPEAT_ERROR);
        }
    }

    private void beforeCreate(CreateRoleReqVo reqVo) {
        String roleName = reqVo.getRoleName();
        String roleCode = reqVo.getRoleCode();
        LambdaQueryWrapper<RoleEntity> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(RoleEntity::getRoleName, roleName);
        Integer count1 = this.baseMapper.selectCount(wrapper1);
        if (count1 > 0) {
            throw new Blog4jException(ErrorEnum.ROLE_NAME_REPEAT_ERROR);
        }

        LambdaQueryWrapper<RoleEntity> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(RoleEntity::getRoleCode, roleCode);
        Integer count2 = this.baseMapper.selectCount(wrapper2);
        if (count2 > 0) {
            throw new Blog4jException(ErrorEnum.ROLE_CODE_REPEAT_ERROR);
        }
    }
}
