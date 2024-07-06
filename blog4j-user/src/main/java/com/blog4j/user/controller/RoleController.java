package com.blog4j.user.controller;

import com.blog4j.common.model.Result;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @return 角色信息
     */
    @GetMapping("/roleList")
    public Result roleList() {
        List<RoleEntity> list = roleService.list();
        return Result.ok(list);
    }
}
