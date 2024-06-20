package com.blog4j.auth.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : Sa-Token 整合 jwt (Simple 简单模式)
 * @Create on : 2024/6/20 20:49
 **/
@Configuration
public class SaTokenConfigure {
    // Sa-Token 整合 jwt (Simple 简单模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
