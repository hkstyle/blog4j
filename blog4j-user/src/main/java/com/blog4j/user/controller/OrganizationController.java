package com.blog4j.user.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.Result;
import com.blog4j.user.service.OrganizationService;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.DeleteOrganizationReqVo;
import com.blog4j.user.vo.req.OrganizationListReqVo;
import com.blog4j.user.vo.req.RemoveOrganizationUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.OrganizationInfoRespVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
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
 * @Create on : 2024/7/8 12:58
 **/
@RequestMapping("/organization")
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    /**
     * 组织列表查询
     *
     * @param reqVo 查询条件
     * @return 组织列表
     */
    @SaCheckPermission(value = "ORGANIZATION:LIST")
    @PostMapping("/list")
    public Result list(@RequestBody OrganizationListReqVo reqVo) {
        List<OrganizationInfoRespVo> list = organizationService.organizationList(reqVo);
        return Result.ok(new PageInfo<>(list));
    }

    /**
     * 查询组织用户列表
     *
     * @param reqVo 查询条件
     * @return 用户列表
     */
    @SaCheckPermission(value = "ORGANIZATION:USER:LIST")
    @PostMapping("/organizationUserList")
    public Result organizationUserList(@RequestBody @Valid UserListReqVo reqVo) {
        List<UserListRespVo> list = userService.organizationUserList(reqVo);
        return Result.ok(new PageInfo<>(list));
    }

    /**
     * 根据组织ID查询组织信息
     *
     * @param organizationId 组织ID
     * @return 组织信息
     */
    @GetMapping("/info/{organizationId}")
    public Result info(@PathVariable("organizationId") String organizationId) {
        OrganizationInfoRespVo respVo = organizationService.info(organizationId);
        return Result.ok(respVo);
    }

    /**
     * 更新组织的状态
     *
     * @param status 状态
     * @param organizationId 组织ID
     * @return 更新成功
     */
    @SaCheckRole(value = "SUPER_ADMIN")
    @GetMapping("/updateOrganizationStatus/{status}/{organizationId}")
    public Result updateOrganizationStatus(@PathVariable("status") Integer status,
                                           @PathVariable("organizationId") String organizationId) {
        organizationService.updateOrganizationStatus(status, organizationId);
        return Result.ok();
    }

    /**
     * 删除组织信息
     *
     * @param reqVo 待删除的组织ID集合
     * @return 删除成功
     */
    @SaCheckPermission(value = "ORGANIZATION:DELETE")
    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteOrganizationReqVo reqVo) {
        organizationService.delete(reqVo);
        return Result.ok();
    }

    /**
     * 移除组织的用户
     *
     * @param reqVo 请求信息
     * @return 移除成功
     */
    @SaCheckPermission(value = "ORGANIZATION:USER:DELETE")
    @PostMapping("/removeOrganizationUser")
    public Result removeOrganizationUser(@RequestBody @Valid RemoveOrganizationUserReqVo reqVo) {
        organizationService.removeOrganizationUser(reqVo);
        return Result.ok();
    }
}
