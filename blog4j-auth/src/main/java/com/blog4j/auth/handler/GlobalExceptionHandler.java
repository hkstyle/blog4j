package com.blog4j.auth.handler;

import cn.dev33.satoken.exception.NotPermissionException;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/20 21:56
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获权限不足的异常
     *
     * @param exception 异常
     * @return 统一响应体
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result NotPermissionException(NotPermissionException exception) {
        log.error("访问接口，权限不足...");
        return Result.error(ErrorEnum.NO_PERMISSION);
    }

}
