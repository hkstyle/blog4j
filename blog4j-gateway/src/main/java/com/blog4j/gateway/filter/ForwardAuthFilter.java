package com.blog4j.gateway.filter;

import cn.hutool.core.util.RandomUtil;
import com.blog4j.common.constants.CommonConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，为请求添加 Same-Token
 */
@Component
public class ForwardAuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                // 为请求追加 Same-Token 参数
                .header(CommonConstant.GATEWAY_AUTHENTICATION, RandomUtil.randomString(6))
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }
}
