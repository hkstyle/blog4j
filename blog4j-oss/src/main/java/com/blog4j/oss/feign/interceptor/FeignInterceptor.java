package com.blog4j.oss.feign.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.blog4j.common.constants.CommonConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : feign请求拦截器
 * @Create on : 2024/7/18 12:39
 **/
@Configuration
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 请求头中添加登录认证token
        String tokenValue = StpUtil.getTokenValue();
        requestTemplate.header(CommonConstant.AUTHENTICATION, tokenValue);
    }
}
