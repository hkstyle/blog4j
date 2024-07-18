package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.api.vo.RoleInfoVo;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.vo.req.CreateRoleReqVo;
import com.blog4j.user.vo.req.DeleteRoleReqVo;
import com.blog4j.user.vo.req.EditRoleReqVo;
import com.blog4j.user.vo.req.RoleListReqVo;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 13:22
 **/
public interface RoleService extends IService<RoleEntity>  {
    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    RoleInfoVo getRoleInfoByUserId(String userId);

    /**
     * 获取所有的角色信息
     *
     * @param reqVo 查询条件
     * @return 角色信息
     */
    List<RoleEntity> roleList(RoleListReqVo reqVo);

    /**
     * 创建角色信息
     *
     * @param reqVo 角色信息
     */
    void create(CreateRoleReqVo reqVo);

    /**
     * 编辑角色信息
     *
     * @param reqVo 角色信息
     */
    void edit(EditRoleReqVo reqVo);

    /**
     * 删除角色
     *
     * @param reqVo 角色信息
     */
    void delete(DeleteRoleReqVo reqVo);
}
