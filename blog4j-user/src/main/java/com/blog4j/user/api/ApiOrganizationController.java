package com.blog4j.user.api;

import com.blog4j.common.model.Result;
import com.blog4j.common.vo.OrganizationVo;
import com.blog4j.user.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/29 10:58
 **/
@RestController
@RequestMapping("/api/organization")
public class ApiOrganizationController {
    @Autowired
    private OrganizationService organizationService;

    /**
     * 根据用户ID获取组织信息
     *
     * @param userId 用户ID
     * @return 组织信息
     */
    @GetMapping("/getOrganizationInfoByUserId/{userId}")
    public Result getOrganizationInfoByUserId(@PathVariable("userId") String userId) {
        return Result.ok(organizationService.getOrganizationInfoByUserId(userId));
    }

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param admin 组织管理员ID
     * @return 组织下所有的用户ID列表
     */
    @GetMapping("/getUserIdsByOrganizationAdmin/{admin}")
    public Result getUserIdsByOrganizationAdmin(@PathVariable("admin") String admin) {
        return Result.ok(organizationService.getUserIdsByOrganizationAdmin(admin));
    }
}
