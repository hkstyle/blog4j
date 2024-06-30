package com.blog4j.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 启动类
 * @Create on : 2024/6/26 20:45
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.blog4j.article.feign")
@EnableDiscoveryClient
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}