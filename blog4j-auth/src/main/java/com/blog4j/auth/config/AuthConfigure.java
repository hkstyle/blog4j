package com.blog4j.auth.config;

import com.blog4j.common.interceptor.ApiCheckInterceptor;
import com.blog4j.common.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 系统配置
 * @Create on : 2024/6/20 22:48
 **/
@Configuration
public class AuthConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCheckInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }
}
