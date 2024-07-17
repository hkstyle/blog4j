package com.blog4j.system.feign;

import com.blog4j.common.model.FResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:33
 **/
@FeignClient("blog4j-user")
public interface UserFeignService {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/api/user/getUserInfoByUserName/{userName}")
    FResult getUserInfoByUserName(@PathVariable("userName") String userName);

    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    @GetMapping("/api/organization/getOrganizationInfoByUserId/{userId}")
    FResult getOrganizationInfoByUserId(@PathVariable("userId") String userId);

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    @GetMapping("/api/organization/getUserIdsByOrganizationAdmin/{admin}")
    FResult getUserIdsByOrganizationAdmin(@PathVariable("admin") String admin);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/api/user/getUserInfoByUserId/{userId}")
    FResult getUserInfoByUserId(@PathVariable("userId") String userId);

    /**
     * 根据用户ID获取权限信息
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    @GetMapping("/api/permission/getPermissionListByUserId/{userId}")
    FResult getPermissionListByUserId(@PathVariable("userId") String userId);
}
