package com.blog4j.api.client;

import com.blog4j.api.vo.OrganizationVo;
import com.blog4j.api.vo.UserInfoVo;
import com.blog4j.api.vo.EditUserLastLoginTimeReqVo;
import com.blog4j.api.vo.PermissionVo;
import com.blog4j.api.vo.RoleInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 13:32
 **/
@FeignClient(value = "blog4j-user", path = "/feign")
public interface FeignUser {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserName/{userName}")
    UserInfoVo getUserInfoByUserName(@PathVariable("userName") String userName);

    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    @GetMapping("/getOrganizationInfoByUserId/{userId}")
    List<OrganizationVo> getOrganizationInfoByUserId(@PathVariable("userId") String userId);

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    @GetMapping("/getUserIdsByOrganizationAdmin/{admin}")
    List<String> getUserIdsByOrganizationAdmin(@PathVariable("admin") String admin);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserId/{userId}")
    UserInfoVo getUserInfoByUserId(@PathVariable("userId") String userId);

    /**
     * 根据用户ID获取权限信息
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    @GetMapping("/getPermissionListByUserId/{userId}")
    List<PermissionVo> getPermissionListByUserId(@PathVariable("userId") String userId);

    /**
     * 更新用户的最近一次登录时间
     *
     * @param reqVo 请求信息
     */
    @PostMapping("/updateUserLastLoginTime")
    void updateUserLastLoginTime(@RequestBody @Valid EditUserLastLoginTimeReqVo reqVo);

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @GetMapping("/getRoleInfoByUserId/{userId}")
    RoleInfoVo getRoleInfoByUserId(@PathVariable("userId") String userId);
}
