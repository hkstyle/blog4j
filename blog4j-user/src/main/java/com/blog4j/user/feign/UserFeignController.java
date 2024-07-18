package com.blog4j.user.feign;

import com.blog4j.api.client.FeignUser;
import com.blog4j.api.vo.OrganizationVo;
import com.blog4j.api.vo.RoleInfoVo;
import com.blog4j.api.vo.UserInfoVo;
import com.blog4j.api.vo.EditUserLastLoginTimeReqVo;
import com.blog4j.api.vo.PermissionVo;
import com.blog4j.user.service.OrganizationService;
import com.blog4j.user.service.PermissionService;
import com.blog4j.user.service.RoleService;
import com.blog4j.user.service.UserService;
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
 * @Create on : 2024/7/18 21:10
 **/
@RestController
@RequestMapping("/feign")
public class UserFeignController implements FeignUser {
    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserName/{userName}")
    public UserInfoVo getUserInfoByUserName(@PathVariable("userName") String userName) {
        return userService.getUserInfoByUserName(userName);
    }

    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    @GetMapping("/getOrganizationInfoByUserId/{userId}")
    public List<OrganizationVo> getOrganizationInfoByUserId(@PathVariable("userId") String userId) {
        return organizationService.getOrganizationInfoByUserId(userId);
    }

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    @GetMapping("/getUserIdsByOrganizationAdmin/{admin}")
    public List<String> getUserIdsByOrganizationAdmin(@PathVariable("admin") String admin) {
        return organizationService.getUserIdsByOrganizationAdmin(admin);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserId/{userId}")
    public UserInfoVo getUserInfoByUserId(@PathVariable("userId") String userId) {
        return userService.getUserInfoByUserId(userId);
    }

    /**
     * 根据用户ID获取权限信息
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    @GetMapping("/getPermissionListByUserId/{userId}")
    public List<PermissionVo> getPermissionListByUserId(@PathVariable("userId") String userId) {
        return permissionService.getPermissionListByUserId(userId);
    }

    /**
     * 更新用户的最近一次登录时间
     *
     * @param reqVo 请求信息
     */
    @PostMapping("/updateUserLastLoginTime")
    public void updateUserLastLoginTime(@RequestBody @Valid EditUserLastLoginTimeReqVo reqVo) {
        userService.updateUserLastLoginTime(reqVo);
    }

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    @GetMapping("/getRoleInfoByUserId/{userId}")
    public RoleInfoVo getRoleInfoByUserId(@PathVariable("userId") String userId) {
        return roleService.getRoleInfoByUserId(userId);
    }
}
