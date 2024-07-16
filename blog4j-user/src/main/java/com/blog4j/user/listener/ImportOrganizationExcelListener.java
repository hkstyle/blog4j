package com.blog4j.user.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.user.entity.OrganizationEntity;
import com.blog4j.user.mapper.OrganizationMapper;
import com.blog4j.user.model.ImportOrganizationExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/12 12:13
 **/
@Slf4j
public class ImportOrganizationExcelListener implements ReadListener<ImportOrganizationExcel> {

    // TODO 允许导入组织的条数  从系统服务获取
    private static final int BATCH_IMPORT_COUNT = 100;

    private static int IMPORT_COUNT = 0;

    List<ImportOrganizationExcel> res;
    OrganizationMapper organizationMapper;
    public ImportOrganizationExcelListener(List<ImportOrganizationExcel> res, OrganizationMapper organizationMapper) {
        this.res = res;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public void invoke(ImportOrganizationExcel organizationExcel, AnalysisContext context) {
        IMPORT_COUNT++;
        log.info("解析到一条数据:{}", JSON.toJSONString(organizationExcel));
        String errMsg = this.checkOrganizationExcelData(organizationExcel);
        if (StringUtils.isNotBlank(errMsg)) {
            organizationExcel.setErrMsg(errMsg);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (IMPORT_COUNT > BATCH_IMPORT_COUNT) {
            throw new Blog4jException(ErrorEnum.IMPORT_USER_MAX_COUNT_ERROR);
        }
        log.info("所有数据解析完成！");
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败: [{}]", exception.getMessage());
        throw new Blog4jException(ErrorEnum.UPLOAD_FILE_ERROR);
    }

    private String checkOrganizationExcelData(ImportOrganizationExcel organizationExcel) {
        StringBuilder sb = new StringBuilder();
        Integer capacity = organizationExcel.getCapacity();
        String organizationName = organizationExcel.getOrganizationName();
        if (Objects.isNull(capacity)) {
            sb.append(ErrorEnum.ORGANIZATION_CAPACITY_NULL_ERROR.getErrorMsg())
                    .append(CommonConstant.COMMA);
        } else {
            // TODO 从系统服务获取单个组织的最大容纳人数
            if (capacity > 100) {
                sb.append(ErrorEnum.ORGANIZATION_CAPACITY_MAX_ERROR.getErrorMsg())
                        .append(CommonConstant.COMMA);
            }
        }

        if (StringUtils.isBlank(organizationName)) {
            sb.append(ErrorEnum.ORGANIZATION_NAME_EMPTY_ERROR.getErrorMsg())
                    .append(CommonConstant.COMMA);
        } else {
            LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<OrganizationEntity>()
                    .eq(OrganizationEntity::getOrganizationName, organizationName);
            Integer count =  organizationMapper.selectCount(wrapper);
            if (count > 0) {
                sb.append(ErrorEnum.ORGANIZATION_NAME_REPEAT_ERROR.getErrorMsg())
                        .append(CommonConstant.COMMA);
            }
        }
        String errMsgStr = sb.toString();
        return CommonUtil.handleString(errMsgStr);
    }
}
