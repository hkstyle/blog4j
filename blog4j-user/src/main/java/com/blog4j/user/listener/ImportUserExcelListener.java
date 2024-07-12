package com.blog4j.user.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.UserSexEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.ValidateUtil;
import com.blog4j.user.model.UserExcel;
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
public class ImportUserExcelListener implements ReadListener<UserExcel> {

    // 允许导入用户的条数 TODO 从系统服务获取
    private static final int BATCH_IMPORT_COUNT = 100;

    private static int IMPORT_COUNT = 0;

    List<UserExcel> res;
    public ImportUserExcelListener(List<UserExcel> res) {
        this.res = res;
    }

    @Override
    public void invoke(UserExcel userExcel, AnalysisContext context) {
        /*ReadRowHolder readRowHolder = context.readRowHolder();
        Integer rowIndex = readRowHolder.getRowIndex();*/
        IMPORT_COUNT++;
        log.info("解析到一条数据:{}", JSON.toJSONString(userExcel));
        String errMsg = this.checkUserExcelData(userExcel);
        if (StringUtils.isNotBlank(errMsg)) {
            userExcel.setErrMsg(errMsg);
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

    private String checkUserExcelData(UserExcel userExcel) {
        StringBuilder sb = new StringBuilder();
        String userName = userExcel.getUserName();
        Integer sex = userExcel.getSex();
        String phone = userExcel.getPhone();
        String email = userExcel.getEmail();

        if (StringUtils.isNotBlank(userName) && userName.length() > 20) {
            sb.append("用户名称校验失败,");
        }

        if (Objects.nonNull(sex)
                && !Objects.equals(sex, UserSexEnum.MAN.getCode())
                && !Objects.equals(sex, UserSexEnum.WOMAN.getCode())
                && !Objects.equals(sex, UserSexEnum.SECRET.getCode())) {
            sb.append("用户性别校验失败,");
        }

        if (StringUtils.isNotBlank(phone) && !ValidateUtil.isValidMobile(phone)) {
            sb.append("用户手机号码校验失败,");
        }

        if (StringUtils.isNotBlank(email) && !ValidateUtil.isValidEmail(email)) {
            sb.append("用户邮箱校验失败");
        }

        return sb.toString();
    }
}
