package com.blog4j.user.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.Result;
import com.blog4j.user.service.OrganizationService;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 获取所有的组织信息
     *
     * @return 组织信息
     */
    @GetMapping("/list")
    public Result list() {
        return Result.ok(organizationService.list());
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
     * 创建组织用户信息
     *
     * @param reqVo 用户信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "ORGANIZATION:USER:ADD")
    @PostMapping("/createOrganizationUser")
    public Result createOrganizationUser(@RequestBody @Valid CreateUserReqVo reqVo) {
        if (StringUtils.isBlank(reqVo.getOrganizationId())) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        userService.create(reqVo);
        return Result.ok();
    }
}
