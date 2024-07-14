package com.blog4j.user.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.blog4j.user.converter.UserSexConverter;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/11 21:12
 **/
@ColumnWidth(40)
@Data
public class UserExcel {
    @ExcelProperty("用户名称")
    private String userName;

    @ExcelProperty(value = "性别", converter = UserSexConverter.class)
    private Integer sex;

    @ExcelProperty("手机号码")
    private String phone;

    @ExcelProperty("邮箱地址")
    private String email;

    @ExcelProperty("地址")
    private String address;

    /**
     * 错误信息
     */
    @ExcelIgnore
    private String errMsg;
}
