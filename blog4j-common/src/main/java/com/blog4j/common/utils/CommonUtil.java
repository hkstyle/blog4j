package com.blog4j.common.utils;

import com.blog4j.common.constants.CommonConstant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/1 21:30
 **/
public class CommonUtil {
    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getCurrentDateTime() {
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(CommonConstant.DATE_TIME_FORMAT);
        return format.format(currentDate);
    }
}
