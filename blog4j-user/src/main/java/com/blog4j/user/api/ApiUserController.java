package com.blog4j.user.api;

import com.blog4j.common.model.Result;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:03
 **/
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserName/{userName}")
    public Result getUserInfoByUserName(@PathVariable("userName") String userName) {
        UserInfoVo userInfoVo = userService.getUserInfoByUserName(userName);
        return Result.ok(userInfoVo);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByUserId/{userId}")
    public Result getUserInfoByUserId(@PathVariable("userId") String userId) {
        UserInfoVo userInfoVo = userService.getUserInfoByUserId(userId);
        return Result.ok(userInfoVo);
    }

}
