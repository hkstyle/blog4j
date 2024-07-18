package com.blog4j.common.utils;

import com.blog4j.common.constants.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 公共工具类
 * @Create on : 2024/7/1 21:30
 **/
@Slf4j
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

    /**
     * MultipartFile转化为File
     *
     * @param multipartFile multipartFile
     * @return file
     * @throws IOException 异常信息
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        return file;
    }

    /**
     * 去掉字符串最后一个字符的逗号
     *
     * @param str 待处理的字符串
     * @return 处理后的字符串
     */
    public static String handleString(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String lastChar = str.substring(str.length() - 1);
        if (StringUtils.equals(lastChar, CommonConstant.COMMA)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
