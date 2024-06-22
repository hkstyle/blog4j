package com.blog4j.auth.api;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.feign.UserFeignService;
import com.blog4j.auth.service.AuthService;
import com.blog4j.auth.vo.req.LoginReqVo;
import com.blog4j.common.model.Result;
import com.blog4j.common.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 认证接口
 * @Create on : 2024/6/20 12:45
 **/
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/login")
    public Result login() {
        UserInfoVo user = userFeignService.getUserInfoByUserName("张三");
        StpUtil.login("123456", SaLoginConfig.setExtra("name", "张三"));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return Result.ok(tokenInfo);
    }

    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody @Valid LoginReqVo loginReqVo) {
        authService.login(LoginContext.builder()
                .userName(loginReqVo.getUserName())
                .password(loginReqVo.getPassword())
                .isAdmin(true).build());
        return Result.ok();
    }
}
