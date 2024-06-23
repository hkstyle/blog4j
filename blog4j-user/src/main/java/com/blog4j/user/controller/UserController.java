package com.blog4j.user.controller;

import com.blog4j.common.model.Result;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/info/{userId}")
    public Result info(@PathVariable("userId") String userId) {
        UserInfoVo userInfoVo = userService.getUserInfoByUserId(userId);
        return Result.ok(userInfoVo);
    }
}
