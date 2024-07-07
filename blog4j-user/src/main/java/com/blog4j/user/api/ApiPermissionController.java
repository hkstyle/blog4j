package com.blog4j.user.api;

import com.blog4j.common.model.Result;
import com.blog4j.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 19:07
 **/
@RestController
@RequestMapping("/api/permission")
public class ApiPermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 根据用户ID获取权限信息
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    @GetMapping("/getPermissionListByUserId/{userId}")
    public Result getPermissionListByUserId(@PathVariable("userId") String userId) {
        return Result.ok(permissionService.getPermissionListByUserId(userId));
    }
}
