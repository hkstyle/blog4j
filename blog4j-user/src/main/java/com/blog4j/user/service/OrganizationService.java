package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.common.vo.OrganizationVo;
import com.blog4j.user.entity.OrganizationEntity;
import com.blog4j.user.vo.req.CreateOrganizationReqVo;
import com.blog4j.user.vo.req.DeleteOrganizationReqVo;
import com.blog4j.user.vo.req.OrganizationListReqVo;
import com.blog4j.user.vo.req.RemoveOrganizationUserReqVo;
import com.blog4j.user.vo.resp.OrganizationInfoRespVo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/29 10:57
 **/
public interface OrganizationService extends IService<OrganizationEntity>  {
    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    List<OrganizationVo> getOrganizationInfoByUserId(String userId);

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    List<String> getUserIdsByOrganizationAdmin(String admin);

    /**
     * 根据组织ID查询组织信息
     *
     * @param organizationId 组织ID
     * @return 组织信息
     */
    OrganizationInfoRespVo info(String organizationId);

    /**
     * 组织列表查询
     *
     * @param reqVo 查询条件
     * @return 组织列表
     */
    List<OrganizationInfoRespVo> organizationList(OrganizationListReqVo reqVo);

    /**
     * 更新组织的状态
     *
     * @param status 状态
     * @param organizationId 组织ID
     */
    void updateOrganizationStatus(Integer status, String organizationId);

    /**
     * 删除组织信息
     *
     * @param reqVo 待删除的组织ID集合
     */
    void delete(DeleteOrganizationReqVo reqVo);

    /**
     * 移除组织的用户
     *
     * @param reqVo 请求信息
     */
    void removeOrganizationUser(RemoveOrganizationUserReqVo reqVo);

    /**
     * 创建组织信息
     *
     * @param reqVo 组织信息
     */
    void create(CreateOrganizationReqVo reqVo);
}
