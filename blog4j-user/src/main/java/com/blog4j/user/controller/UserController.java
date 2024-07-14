package com.blog4j.user.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blog4j.common.model.Result;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.model.UserExcel;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.BatchCreateUserReqVo;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.DeleteUserReqVo;
import com.blog4j.user.vo.req.EditUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/23 10:57
 **/
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @SaCheckLogin
    @GetMapping("/info/{userId}")
    public Result info(@PathVariable("userId") String userId) {
        UserInfoVo userInfoVo = userService.getUserInfoByUserId(userId);
        return Result.ok(userInfoVo);
    }

    /**
     * 查询用户列表
     *
     * @param reqVo 查询条件
     * @return 用户列表
     */
    @SaCheckPermission(value = "USER:LIST")
    @PostMapping("/list")
    public Result list(@RequestBody @Valid UserListReqVo reqVo) {
        List<UserListRespVo> list = userService.userList(reqVo);
        return Result.ok(new PageInfo<>(list));
    }

    /**
     * 创建用户信息
     *
     * @param reqVo 用户信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "USER:ADD")
    @PostMapping("/create")
    public Result create(@RequestBody @Valid CreateUserReqVo reqVo) {
        userService.create(reqVo);
        return Result.ok();
    }

    /**
     * 编辑用户信息
     *
     * @param reqVo 用户信息
     * @return 编辑成功
     */
    @SaCheckPermission(value = "USER:EDIT")
    @PostMapping("/update")
    public Result update(@RequestBody EditUserReqVo reqVo) {
        userService.edit(reqVo);
        return Result.ok();
    }

    /**
     * 删除用户
     *
     * @param reqVo 用户信息
     * @return 删除成功
     */
    @SaCheckPermission(value = "USER:DELETE")
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteUserReqVo reqVo) {
        userService.delete(reqVo);
        return Result.ok();
    }

    /**
     * 用户批量导入
     *
     * @param file 文件
     * @return 导入成功
     */
    @SaCheckPermission(value = "USER:IMPORT")
    @PostMapping("/importUser")
    public Result importUser(@RequestParam("file") MultipartFile file) {
        List<UserExcel> list = userService.importUser(file);
        return Result.ok(list);
    }

    /**
     * 批量创建用户信息
     *
     * @param reqVo 用户信息
     * @return 创建成功
     */
    @SaCheckPermission(value = "USER:ADD")
    @PostMapping("/batchCreate")
    public Result batchCreate(@RequestBody @Valid BatchCreateUserReqVo reqVo) {
        userService.batchCreate(reqVo);
        return Result.ok();
    }
}
