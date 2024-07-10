package com.blog4j.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.OrganizationApproveStatus;
import com.blog4j.common.enums.OrganizationStatusEnum;
import com.blog4j.common.enums.RoleEnum;
import com.blog4j.common.enums.UserStatusEnum;
import com.blog4j.common.enums.YesOrNoEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.common.vo.OrganizationVo;
import com.blog4j.user.entity.OrganizationEntity;
import com.blog4j.user.entity.OrganizationUserRelEntity;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.OrganizationMapper;
import com.blog4j.user.mapper.OrganizationUserRelMapper;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.service.OrganizationService;
import com.blog4j.user.vo.req.ApproveOrganizationReqVo;
import com.blog4j.user.vo.req.CreateOrganizationReqVo;
import com.blog4j.user.vo.req.DeleteOrganizationReqVo;
import com.blog4j.user.vo.req.OrganizationListReqVo;
import com.blog4j.user.vo.req.RemoveOrganizationUserReqVo;
import com.blog4j.user.vo.resp.OrganizationInfoRespVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/29 10:57
 **/
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, OrganizationEntity>
        implements OrganizationService {

    @Autowired
    private OrganizationUserRelMapper organizationUserRelMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    @Override
    public List<OrganizationVo> getOrganizationInfoByUserId(String userId) {
        // 根据用户信息查询组织信息
        LambdaQueryWrapper<OrganizationUserRelEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationUserRelEntity::getUserId, userId);
        List<OrganizationUserRelEntity> organizationUserRelList = organizationUserRelMapper.selectList(queryWrapper);
        if (organizationUserRelList.isEmpty()) {
            return null;
        }

        return organizationUserRelList.stream().map(organizationUserRel -> {
            LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrganizationEntity::getOrganizationId, organizationUserRel.getOrganizationId());
            OrganizationEntity organization = this.baseMapper.selectOne(wrapper);
            if (Objects.isNull(organization)) {
                throw new Blog4jException(ErrorEnum.ORGANIZATION_INFO_EMPTY_ERROR);
            }
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(organization, organizationVo);
            return organizationVo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    @Override
    public List<String> getUserIdsByOrganizationAdmin(String admin) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrganizationEntity::getOrganizationAdmin, admin);
        OrganizationEntity organization = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(organization)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        LambdaQueryWrapper<OrganizationUserRelEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrganizationUserRelEntity::getOrganizationId, organization.getOrganizationId());
        List<OrganizationUserRelEntity> organizationUserRelEntityList = organizationUserRelMapper.selectList(queryWrapper);
        if (organizationUserRelEntityList.isEmpty()) {
            throw new Blog4jException(ErrorEnum.SYSTEM_ERROR);
        }

        return organizationUserRelEntityList.stream().map(OrganizationUserRelEntity::getUserId)
                .collect(Collectors.toList());
    }

    /**
     * 根据组织ID查询组织信息
     *
     * @param organizationId 组织ID
     * @return 组织信息
     */
    @Override
    public OrganizationInfoRespVo info(String organizationId) {
        OrganizationEntity organization = this.baseMapper.selectById(organizationId);
        if (Objects.isNull(organization)) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_INFO_EMPTY_ERROR);
        }
        OrganizationInfoRespVo respVo = new OrganizationInfoRespVo();
        BeanUtils.copyProperties(organization, respVo);
        return respVo;
    }

    /**
     * 组织列表查询
     *
     * @param reqVo 查询条件
     * @return 组织列表
     */
    @Override
    public List<OrganizationInfoRespVo> organizationList(OrganizationListReqVo reqVo) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrganizationEntity::getStatus, OrganizationStatusEnum.NORMAL.getCode());
        wrapper.eq(OrganizationEntity::getApproveStatus, OrganizationApproveStatus.PASS.getCode());
        if (Objects.nonNull(reqVo.getStatus())) {
            wrapper.eq(OrganizationEntity::getStatus, reqVo.getStatus());
        }

        if (StringUtils.isNotBlank(reqVo.getOrganizationName())) {
            wrapper.like(OrganizationEntity::getOrganizationName, reqVo.getOrganizationName());
        }

        if (Objects.nonNull(reqVo.getPageNo()) && Objects.nonNull(reqVo.getPageSize())) {
            PageHelper.startPage(reqVo.getPageNo(), reqVo.getPageSize());
        }

        List<OrganizationEntity> organizationEntityList = this.baseMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(organizationEntityList)) {
            return new ArrayList<>();
        }
        return organizationEntityList.stream().map(item -> {
            OrganizationInfoRespVo respVo = new OrganizationInfoRespVo();
            BeanUtils.copyProperties(item, respVo);
            return respVo;
        }).collect(Collectors.toList());
    }

    /**
     * 更新组织的状态
     *
     * @param status         状态
     * @param organizationId 组织ID
     */
    @Override
    public void updateOrganizationStatus(Integer status, String organizationId) {
        if (!Objects.equals(status, OrganizationStatusEnum.LOCK.getCode()) &&
                !Objects.equals(status, OrganizationStatusEnum.NORMAL.getCode())) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        OrganizationEntity organization = this.baseMapper.selectById(organizationId);
        if (Objects.isNull(organization)) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_INFO_EMPTY_ERROR);
        }

        organization.setStatus(status).setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(organization);
    }

    /**
     * 删除组织信息
     *
     * @param reqVo 待删除的组织ID集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(DeleteOrganizationReqVo reqVo) {
        List<String> organizationIds = reqVo.getOrganizationIds();
        // 删除这些组织下的用户的关系
        LambdaQueryWrapper<OrganizationUserRelEntity> wrapper = new LambdaQueryWrapper<OrganizationUserRelEntity>()
                .in(OrganizationUserRelEntity::getOrganizationId, organizationIds);
        List<OrganizationUserRelEntity> organizationUserRelEntityList
                = organizationUserRelMapper.selectList(wrapper);
        if (CollectionUtil.isNotEmpty(organizationUserRelEntityList)) {
            Set<Integer> ids = organizationUserRelEntityList.stream()
                    .map(OrganizationUserRelEntity::getId).collect(Collectors.toSet());
            organizationUserRelMapper.deleteBatchIds(ids);
        }

        this.baseMapper.deleteBatchIds(organizationIds);
    }

    /**
     * 移除组织的用户
     *
     * @param reqVo 请求信息
     */
    @Override
    public void removeOrganizationUser(RemoveOrganizationUserReqVo reqVo) {
        String organizationId = reqVo.getOrganizationId();
        List<String> userIds = reqVo.getUserIds();

        OrganizationEntity organization = this.baseMapper.selectById(organizationId);
        if (Objects.isNull(organization)) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_INFO_EMPTY_ERROR);
        }

        if (Objects.equals(OrganizationStatusEnum.LOCK.getCode(), organization.getStatus())) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_LOCK_ERROR);
        }

        // 不能删除该组织的管理员
        if (userIds.contains(organization.getOrganizationAdmin())) {
            throw new Blog4jException(ErrorEnum.DELETE_ORGANIZATION_ADMIN_ERROR);
        }

        LambdaQueryWrapper<OrganizationUserRelEntity> wrapper = new LambdaQueryWrapper<OrganizationUserRelEntity>()
                .eq(OrganizationUserRelEntity::getOrganizationId, organizationId)
                .in(OrganizationUserRelEntity::getUserId, userIds);
        List<OrganizationUserRelEntity> organizationUserRelEntityList = organizationUserRelMapper.selectList(wrapper);
        if (CollectionUtil.isNotEmpty(organizationUserRelEntityList)) {
            Set<Integer> ids = organizationUserRelEntityList.stream()
                    .map(OrganizationUserRelEntity::getId).collect(Collectors.toSet());
            organizationUserRelMapper.deleteBatchIds(ids);
        }
    }

    /**
     * 创建组织信息
     *
     * @param reqVo 组织信息
     */
    @Override
    public void create(CreateOrganizationReqVo reqVo) {
        UserEntity user = this.beforeCreate(reqVo);
        OrganizationEntity organization = OrganizationEntity.builder()
                .organizationId(IdGeneratorSnowflakeUtil.snowflakeId())
                .capacity(reqVo.getCapacity())
                .organizationName(reqVo.getOrganizationName())
                .organizationAvatar(reqVo.getOrganizationAvatar())
                .organizationCreater(user.getUserId())
                .organizationCreaterAvatar(user.getAvatar())
                .organizationCreaterName(user.getUserName())
                .slogan(reqVo.getSlogan())
                .status(OrganizationStatusEnum.LOCK.getCode())
                .updateTime(CommonUtil.getCurrentDateTime())
                .createTime(CommonUtil.getCurrentDateTime())
                .deleted(YesOrNoEnum.NO.getCode())
                .approveStatus(OrganizationApproveStatus.WAIT_APPROVE.getCode())
                .build();
        this.baseMapper.insert(organization);
    }

    /**
     * 审批组织
     *
     * @param reqVo 审批信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approveOrganization(ApproveOrganizationReqVo reqVo) {
        Integer approveStatus = reqVo.getApproveStatus();
        String organizationId = reqVo.getOrganizationId();
        String approveMessage = reqVo.getApproveMessage();

        OrganizationEntity organization = this.baseMapper.selectById(organizationId);
        if (Objects.isNull(organization)) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_INFO_EMPTY_ERROR);
        }

        if (Objects.equals(approveStatus, OrganizationApproveStatus.PASS.getCode())) {
            String organizationCreater = organization.getOrganizationCreater();
            UserEntity user = userMapper.selectById(organizationCreater);
            if (Objects.isNull(user)) {
                throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
            }

            LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<RoleEntity>()
                    .eq(RoleEntity::getRoleCode, RoleEnum.ORGANIZATION_ADMIN.getDesc());
            RoleEntity role = roleMapper.selectOne(wrapper);
            if (Objects.isNull(role)) {
                throw new Blog4jException(ErrorEnum.SYSTEM_ERROR);
            }

            user.setRoleId(role.getRoleId());
            userMapper.updateById(user);

            organization.setApproveStatus(approveStatus)
                    .setApproveMessage(approveMessage)
                    .setApproveTime(CommonUtil.getCurrentDateTime())
                    .setUpdateTime(CommonUtil.getCurrentDateTime())
                    .setOrganizationAdmin(organization.getOrganizationCreater())
                    .setOrganizationAdminName(organization.getOrganizationCreaterName())
                    .setStatus(OrganizationStatusEnum.NORMAL.getCode());
        } else {
            organization.setApproveStatus(approveStatus)
                    .setApproveMessage(approveMessage)
                    .setApproveTime(CommonUtil.getCurrentDateTime())
                    .setUpdateTime(CommonUtil.getCurrentDateTime());
        }
        this.baseMapper.updateById(organization);

    }

    private UserEntity beforeCreate(CreateOrganizationReqVo reqVo) {
        String organizationName = reqVo.getOrganizationName();
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<OrganizationEntity>()
                .eq(OrganizationEntity::getOrganizationName, organizationName);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new Blog4jException(ErrorEnum.ORGANIZATION_NAME_REPEAT_ERROR);
        }

        // TODO 组织容纳人数校验

        String userId = StpUtil.getLoginIdAsString();
        UserEntity user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        if (Objects.equals(user.getStatus(), UserStatusEnum.LOCK.getCode())) {
            throw new Blog4jException(ErrorEnum.USER_LOCK_ERROR);
        }

        List<String> roleList = StpUtil.getRoleList();
        String role = roleList.get(0);
        if (!StringUtils.equals(RoleEnum.SUPER_ADMIN.getDesc(), role)) {
            // 非超级管理员 一个用户只能创建一个组织
            LambdaQueryWrapper<OrganizationEntity> wrapper1 = new LambdaQueryWrapper<OrganizationEntity>()
                    .eq(OrganizationEntity::getOrganizationAdmin, userId);
            if (this.baseMapper.selectCount(wrapper1) > 0) {
                throw new Blog4jException(ErrorEnum.USER_ORGANIZATION_MAX_ERROR);
            }
        }
        return user;
    }
}
