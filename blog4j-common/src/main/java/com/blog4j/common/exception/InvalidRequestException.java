package com.blog4j.common.exception;

import com.blog4j.common.enums.ErrorEnum;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 无效的请求异常
 * @Create on : 2024/6/20 22:43
 **/
public class InvalidRequestException extends RuntimeException {
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String errMsg;

    public InvalidRequestException() {
        this.code = ErrorEnum.INVALID_REQUEST_ERROR.getErrorCode();
        this.errMsg = ErrorEnum.INVALID_REQUEST_ERROR.getErrorMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
