package com.blog4j.gateway.filter;

import cn.hutool.core.util.RandomUtil;
import com.blog4j.common.constants.CommonConstant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 给所有请求添加一个请求头
 * @Create on : 2024/6/21 18:54
 **/
@Component
public class AddRequestHeaderFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add(CommonConstant.GATEWAY_AUTHENTICATION, RandomUtil.randomString(6));
        }).build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        // 确保此过滤器是全局过滤器链中的第一个
        return -1;
    }
}
