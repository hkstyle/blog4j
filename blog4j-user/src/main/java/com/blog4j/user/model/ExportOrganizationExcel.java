package com.blog4j.user.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.blog4j.user.converter.OrganizationApproveStatusConverter;
import com.blog4j.user.converter.OrganizationStatusConverter;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 20:57
 **/
@ColumnWidth(40)
@Data
public class ExportOrganizationExcel {
    @ExcelProperty("组织名称")
    private String organizationName;

    @ExcelProperty("组织创建者名称")
    private String organizationCreaterName;

    @ExcelProperty("组织管理员名称")
    private String organizationAdminName;

    @ExcelProperty("组织容量")
    private Integer capacity;

    @ExcelProperty(value = "组织状态", converter = OrganizationStatusConverter.class)
    private Integer status;

    @ExcelProperty(value = "审批状态", converter = OrganizationApproveStatusConverter.class)
    private Integer approveStatus;

    @ExcelProperty("审批时间")
    private String approveTime;

    @ExcelProperty("组织口号")
    private String slogan;
}
