package com.blog4j.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 启动类
 * @Create on : 2024/7/2 12:50
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.blog4j")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}