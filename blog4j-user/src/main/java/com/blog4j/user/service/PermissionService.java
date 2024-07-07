package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.user.entity.PermissionEntity;
import com.blog4j.user.vo.req.CreateNodeReqVo;
import com.blog4j.user.vo.req.DeletePermissionNodeReqVo;
import com.blog4j.user.vo.req.SaveRolePermissionRelReqVo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 00:32
 **/
public interface PermissionService extends IService<PermissionEntity> {
    /**
     * 获取树形结构的权限列表
     *
     * @return 权限列表
     */
    List<PermissionEntity> getTreePermissions();

    /**
     * 根据角色ID查询权限信息列表
     *
     * @param roleId 角色ID
     * @return 权限信息列表
     */
    List<Integer> getPermissionByRoleId(String roleId);

    /**
     * 保存角色与权限的关系
     *
     * @param reqVo 信息
     */
    void saveRolePermissionRel(SaveRolePermissionRelReqVo reqVo);

    /**
     * 删除节点
     *
     * @param reqVo 信息
     */
    void deletePermissionNode(DeletePermissionNodeReqVo reqVo);

    /**
     * 新增权限
     *
     * @param reqVo 信息
     */
    void createNode(CreateNodeReqVo reqVo);
}
