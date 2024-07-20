package com.blog4j.user.config;

import com.blog4j.common.utils.ExcelUtil;
import com.blog4j.common.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 20:13
 **/
@Configuration
public class WebConfigure {
    @Bean
    public ExcelUtil excelUtil() {
        return new ExcelUtil();
    }

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }
}
