package com.blog4j.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.vo.OrganizationVo;
import com.blog4j.user.entity.OrganizationEntity;
import com.blog4j.user.entity.OrganizationUserRelEntity;
import com.blog4j.user.mapper.OrganizationMapper;
import com.blog4j.user.mapper.OrganizationUserRelMapper;
import com.blog4j.user.service.OrganizationService;
import com.blog4j.user.vo.req.OrganizationListReqVo;
import com.blog4j.user.vo.resp.OrganizationInfoRespVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            return null;
        }
        return organizationEntityList.stream().map(item -> {
            OrganizationInfoRespVo respVo = new OrganizationInfoRespVo();
            BeanUtils.copyProperties(item, respVo);
            return respVo;
        }).collect(Collectors.toList());
    }
}
