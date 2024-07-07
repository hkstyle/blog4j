package com.blog4j.user.controller;

import com.blog4j.common.model.Result;
import com.blog4j.user.entity.PermissionEntity;
import com.blog4j.user.service.PermissionService;
import com.blog4j.user.vo.req.CreateNodeReqVo;
import com.blog4j.user.vo.req.DeletePermissionNodeReqVo;
import com.blog4j.user.vo.req.EditNodeReqVo;
import com.blog4j.user.vo.req.SaveRolePermissionRelReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 00:30
 **/
@RequestMapping("/permission")
@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 获取树形结构的权限列表
     *
     * @return 权限列表
     */
    @GetMapping("/getTreePermissions")
    public Result getTreePermissions() {
        List<PermissionEntity> list = permissionService.getTreePermissions();
        return Result.ok(list);
    }

    /**
     * 根据角色ID查询权限信息列表
     *
     * @param roleId 角色ID
     * @return 权限信息列表
     */
    @GetMapping("/getPermissionByRoleId/{roleId}")
    public Result getPermissionByRoleId(@PathVariable("roleId") String roleId) {
        List<Integer> list = permissionService.getPermissionByRoleId(roleId);
        return Result.ok(list);
    }

    /**
     * 保存角色与权限的关系
     *
     * @param reqVo 信息
     * @return 保存成功
     */
    @PostMapping("/saveRolePermissionRel")
    public Result saveRolePermissionRel(@RequestBody @Valid SaveRolePermissionRelReqVo reqVo) {
        permissionService.saveRolePermissionRel(reqVo);
        return Result.ok();
    }

    /**
     * 删除节点
     *
     * @param reqVo 信息
     * @return 删除成功
     */
    @PostMapping("/deletePermissionNode")
    public Result deletePermissionNode(@RequestBody @Valid DeletePermissionNodeReqVo reqVo) {
        permissionService.deletePermissionNode(reqVo);
        return Result.ok();
    }

    /**
     * 新增权限
     *
     * @param reqVo 信息
     * @return 新增成功
     */
    @PostMapping("/createNode")
    public Result createNode(@RequestBody @Valid CreateNodeReqVo reqVo) {
        permissionService.createNode(reqVo);
        return Result.ok();
    }

    /**
     * 编辑权限
     *
     * @param reqVo 信息
     * @return 新增成功
     */
    @PostMapping("/editNode")
    public Result editNode(@RequestBody @Valid EditNodeReqVo reqVo) {
        permissionService.editNode(reqVo);
        return Result.ok();
    }

    /**
     * 根据权限ID获取权限信息
     *
     * @param permissionId 权限ID
     * @return  权限信息
     */
    @GetMapping("/getPermissionById/{permissionId}")
    public Result getPermissionById(@PathVariable("permissionId") Integer permissionId) {
        PermissionEntity permission = permissionService.getById(permissionId);
        return Result.ok(permission);
    }
}
