package com.blog4j.user.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.blog4j.common.enums.OrganizationApproveStatus;
import com.blog4j.common.enums.OrganizationStatusEnum;
import com.blog4j.common.enums.UserSexEnum;

import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/14 21:19
 **/
public class OrganizationApproveStatusConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 从excel读数据时候调用
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        /*String value = cellData.getStringValue();
        if (StringUtils.isBlank(value)){
            // 保密
            return UserSexEnum.SECRET.getCode();
        }
        if (value.indexOf('男') != -1) {
            return UserSexEnum.MAN.getCode();
        }
        if (value.indexOf('女') != -1) {
            return UserSexEnum.WOMAN.getCode();
        }*/
        return UserSexEnum.SECRET.getCode();
    }

    /**
     * 写数据到excel里面
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
        Integer value = context.getValue();
        if (Objects.equals(value, 1)) {
            return new WriteCellData<>(OrganizationApproveStatus.WAIT_APPROVE.getDesc());
        }
        if (Objects.equals(value, 2)) {
            return new WriteCellData<>(OrganizationApproveStatus.PASS.getDesc());
        }
        return new WriteCellData<>(OrganizationApproveStatus.REJECT.getDesc());
    }
}
