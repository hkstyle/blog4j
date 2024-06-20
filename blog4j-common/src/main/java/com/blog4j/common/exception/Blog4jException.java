package com.blog4j.common.exception;

import com.blog4j.common.constants.CommonConstant;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 异常
 * @Create on : 2024/6/20 12:26
 **/
public class Blog4jException extends RuntimeException {
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String errMsg;

    /**
     * 构造方法
     *
     * @param code 错误码
     * @param errMsg 错误描述
     */
    public Blog4jException(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public Blog4jException(String errMsg) {
        this.code = CommonConstant.FAIL_CODE;
        this.errMsg = errMsg;
    }

    public Integer getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
