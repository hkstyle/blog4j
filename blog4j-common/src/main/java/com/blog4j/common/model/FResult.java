package com.blog4j.common.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : Feign远程调用的结果集
 * @Create on : 2024/6/22 19:36
 **/
@AllArgsConstructor
@NoArgsConstructor
public class FResult extends HashMap<String, Object> implements Serializable {
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
    public static FResult ok() {
        return new FResult(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_DESC, null);
    }

    /**
     * 响应成功，带数据
     *
     * @param data 数据
     * @return 响应
     */
    public static FResult ok(Object data) {
        return new FResult(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_DESC, data);
    }

    /**
     * 响应失败
     *
     * @param message 失败描述
     * @return 响应
     */
    public static FResult error(String message) {
        return new FResult(CommonConstant.FAIL_CODE, message, null);
    }

    /**
     * 自定义响应数据
     *
     * @param code 响应码
     * @param message 响应描述
     * @return 响应
     */
    public static FResult build(int code, String message) {
        return new FResult(code, message, null);
    }

    /**
     * 枚举错误响应
     *
     * @param errorEnum 枚举错误
     * @return  响应
     */
    public static FResult error(ErrorEnum errorEnum) {
        return new FResult(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }

    //利用fastjson进行反序列化
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data");	//默认是map
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    //利用fastjson进行反序列化
    public <T> T getData(String key,TypeReference<T> typeReference) {
        Object data = get(key);	//默认是map
        String jsonString = JSON.toJSONString(data);
        T t = JSON.parseObject(jsonString, typeReference);
        return t;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public String getMessage() {
        return (String) this.get("message");
    }
}
