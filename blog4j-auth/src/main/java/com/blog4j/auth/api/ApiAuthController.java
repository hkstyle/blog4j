package com.blog4j.auth.api;

import com.blog4j.common.model.JwtUser;
import com.blog4j.common.model.Result;
import com.blog4j.common.utils.JwtUtil;
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

    @GetMapping("/login")
    public Result login() {
        String jwt = JwtUtil.build(JwtUser.builder().userId("as23gghhnn").userName("李四").build());
        JwtUser parse = JwtUtil.parse(jwt);
        System.out.println(parse);
        return Result.ok(jwt);
    }
}
