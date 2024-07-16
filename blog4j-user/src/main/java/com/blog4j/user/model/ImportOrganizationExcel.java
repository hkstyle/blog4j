package com.blog4j.user.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/15 22:05
 **/
@Data
public class ImportOrganizationExcel {
    @ExcelProperty("组织名称")
    private String organizationName;

    @ExcelProperty("组织口号")
    private String slogan;

    @ExcelProperty("容纳人数")
    private Integer capacity;

    /**
     * 错误信息
     */
    @ExcelIgnore
    private String errMsg;
}
