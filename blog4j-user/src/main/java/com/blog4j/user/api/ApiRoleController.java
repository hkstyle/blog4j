package com.blog4j.user.api;

import com.blog4j.common.model.Result;
import com.blog4j.common.vo.RoleInfoVo;
import com.blog4j.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 13:24
 **/
@RestController
@RequestMapping("/api/role")
public class ApiRoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @GetMapping("/getRoleInfoByUserId/{userId}")
    public Result getRoleInfoByUserId(@PathVariable("userId") String userId) {
        RoleInfoVo roleInfoVo = roleService.getRoleInfoByUserId(userId);
        return Result.ok(roleInfoVo);
    }
}
