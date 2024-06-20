package com.blog4j.auth.api;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 认证接口
 * @Create on : 2024/6/20 12:45
 **/
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @SaCheckPermission("login")
    @GetMapping("/login")
    public Result login() {
        StpUtil.login("123456", SaLoginConfig.setExtra("name", "张三")
                .setTimeout(CommonConstant.JWT_TIMEOUT));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return Result.ok(tokenInfo);
    }
}
