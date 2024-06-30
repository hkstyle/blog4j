package com.blog4j.user;

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
@EnableFeignClients(basePackages = "com.blog4j.user.feign")
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}