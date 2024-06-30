package com.blog4j.user.service.impl;

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
}
