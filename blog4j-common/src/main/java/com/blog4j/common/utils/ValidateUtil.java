package com.blog4j.common.utils;

import com.blog4j.common.model.RegexPatterns;
import org.apache.commons.lang.StringUtils;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/12 12:30
 **/
public class ValidateUtil {
    /**
     * 手机号码校验
     *
     * @param number 手机号码
     * @return 成功或失败
     */
    public static boolean isValidMobile(String number) {
        if (StringUtils.isEmpty(number)) {
            return true;
        }
        return number.matches(RegexPatterns.PHONE_REGEX);
    }

    /**
     * 邮箱校验
     *
     * @param number 邮箱
     * @return 成功或失败
     */
    public static boolean isValidEmail(String number) {
        if (StringUtils.isEmpty(number)) {
            return true;
        }
        return number.matches(RegexPatterns.EMAIL_REGEX);
    }
}
