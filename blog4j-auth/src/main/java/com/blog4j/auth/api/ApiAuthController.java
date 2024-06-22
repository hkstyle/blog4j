package com.blog4j.auth.api;

import com.blog4j.auth.context.LoginContext;
import com.blog4j.auth.service.AuthService;
import com.blog4j.auth.vo.req.LoginReqVo;
import com.blog4j.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginReqVo loginReqVo) {
        LoginContext loginContext = LoginContext.builder()
                .userName(loginReqVo.getUserName())
                .password(loginReqVo.getPassword())
                .isAdmin(true).build();
        authService.login(loginContext);
        return Result.ok(loginContext.getSaTokenInfo());
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
