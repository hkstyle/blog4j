package com.blog4j.oss.config;

import com.blog4j.common.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 12:50
 **/
@Configuration
public class WebConfig {
    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }
}
