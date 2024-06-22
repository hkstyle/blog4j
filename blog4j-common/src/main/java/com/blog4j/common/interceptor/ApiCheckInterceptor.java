package com.blog4j.common.interceptor;

import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 检查请求是否有效，即通过网关转发来的请求
 * @Create on : 2024/6/20 22:34
 **/
@Slf4j
public class ApiCheckInterceptor implements HandlerInterceptor {
    private static final String WHITE_API_RUI_PREFIX = "/api";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("request uri : [{}]", uri);
        if (uri.startsWith(WHITE_API_RUI_PREFIX)) {
            return true;
        }
        String header = request.getHeader(CommonConstant.GATEWAY_AUTHENTICATION);
        if (StringUtils.isBlank(header)) {
            log.error("Invalid request .");
            throw new InvalidRequestException();
        }
        return true;
    }
}
