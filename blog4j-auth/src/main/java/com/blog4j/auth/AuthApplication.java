package com.blog4j.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 启动类
 * @Create on : 2024/6/20 12:45
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.blog4j.auth.feign")
public class AuthApplication {
    /**
     * 启动
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}