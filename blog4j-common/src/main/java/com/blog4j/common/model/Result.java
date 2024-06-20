package com.blog4j.common.model;

import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 公共响应封装结果集
 * @Create on : 2024/6/20 12:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应成功，无数据
     *
     * @return  响应
     */
    public static Result ok() {
        return new Result(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_DESC, null);
    }

    /**
     * 响应成功，带数据
     *
     * @param data 数据
     * @return 响应
     */
    public static Result ok(Object data) {
        return new Result(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_DESC, data);
    }

    /**
     * 响应失败
     *
     * @param message 失败描述
     * @return 响应
     */
    public static Result error(String message) {
        return new Result(CommonConstant.FAIL_CODE, message, null);
    }

    /**
     * 自定义响应数据
     *
     * @param code 响应码
     * @param message 响应描述
     * @return 响应
     */
    public static Result build(int code, String message) {
        return new Result(code, message, null);
    }

    /**
     * 枚举错误响应
     *
     * @param errorEnum 枚举错误
     * @return  响应
     */
    public static Result error(ErrorEnum errorEnum) {
        return new Result(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }
}
