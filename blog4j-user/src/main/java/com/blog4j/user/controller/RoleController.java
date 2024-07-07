package com.blog4j.user.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.common.model.Result;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.service.RoleService;
import com.blog4j.user.vo.req.CreateRoleReqVo;
import com.blog4j.user.vo.req.DeleteRoleReqVo;
import com.blog4j.user.vo.req.EditRoleReqVo;
import com.blog4j.user.vo.req.RoleListReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/6 14:07
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有的角色信息
     *
     * @param reqVo 查询条件
     * @return 角色信息
     */
    //@SaCheckPermission(value = "ROLE:LIST")
    @PostMapping("/roleList")
    public Result roleList(@RequestBody RoleListReqVo reqVo) {
        List<RoleEntity> list = roleService.roleList(reqVo);
        return Result.ok(list);
    }

    /**
     * 根据角色ID获取角色信息
     *
     * @param roleId 角色ID
     * @return 角色信息
     */
    @GetMapping("/getRoleById/{roleId}")
    public Result getRoleById(@PathVariable("roleId") String roleId) {
        RoleEntity role = roleService.getById(roleId);
        return Result.ok(role);
    }

    /**
     * 创建角色信息
     *
     * @param reqVo 角色信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "ROLE:ADD")
    @PostMapping("/create")
    public Result create(@RequestBody CreateRoleReqVo reqVo) {
        roleService.create(reqVo);
        return Result.ok();
    }

    /**
     * 编辑角色信息
     *
     * @param reqVo 角色信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "ROLE:EDIT")
    @PostMapping("/edit")
    public Result edit(@RequestBody EditRoleReqVo reqVo) {
        roleService.edit(reqVo);
        return Result.ok();
    }

    /**
     * 删除角色
     *
     * @param reqVo 角色信息
     * @return 删除成功
     */
    @SaCheckPermission(value = "ROLE:DELETE")
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteRoleReqVo reqVo) {
        roleService.delete(reqVo);
        return Result.ok();
    }
}
