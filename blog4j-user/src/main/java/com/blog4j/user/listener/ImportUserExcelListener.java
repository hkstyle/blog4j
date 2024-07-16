package com.blog4j.user.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.UserSexEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.ValidateUtil;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.mapper.UserMapper;
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
    UserMapper userMapper;
    public ImportUserExcelListener(List<UserExcel> res, UserMapper userMapper) {
        this.res = res;
        this.userMapper = userMapper;
    }

    @Override
    public void invoke(UserExcel userExcel, AnalysisContext context) {
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

        // 校验用户名称
        if (StringUtils.isBlank(userName)) {
            sb.append(ErrorEnum.USER_NAME_EMPTY_ERROR.getErrorMsg())
                    .append(CommonConstant.COMMA);
        } else {
            LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                    .eq(UserEntity::getUserName, userName);
            Integer count = userMapper.selectCount(wrapper);
            if (count > 0) {
                sb.append(ErrorEnum.USERNAME_REPEAT_ERROR.getErrorMsg())
                        .append(CommonConstant.COMMA);
            }
        }

        // 校验用户性别
        if (Objects.nonNull(sex)
                && !Objects.equals(sex, UserSexEnum.MAN.getCode())
                && !Objects.equals(sex, UserSexEnum.WOMAN.getCode())
                && !Objects.equals(sex, UserSexEnum.SECRET.getCode())) {
            sb.append(ErrorEnum.SEX_ERROR.getErrorMsg())
                    .append(CommonConstant.COMMA);
        }

        // 校验手机号
        if (StringUtils.isNotBlank(phone)) {
            if (!ValidateUtil.isValidMobile(phone)) {
                sb.append(ErrorEnum.PHONE_ERROR.getErrorMsg())
                        .append(CommonConstant.COMMA);
            } else {
                LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getPhone, phone);
                Integer count = userMapper.selectCount(wrapper);
                if (count > 0) {
                    sb.append(ErrorEnum.PHONE_REPEAT_ERROR.getErrorMsg())
                            .append(CommonConstant.COMMA);
                }
            }
        }

        // 校验邮箱
        if (StringUtils.isNotBlank(email)) {
            if (!ValidateUtil.isValidEmail(email)) {
                sb.append(ErrorEnum.EMAIL_ERROR.getErrorMsg())
                        .append(CommonConstant.COMMA);
            } else {
                LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getEmail, email);
                Integer count = userMapper.selectCount(wrapper);
                if (count > 0) {
                    sb.append(ErrorEnum.EMAIL_REPEAT_ERROR.getErrorMsg())
                            .append(CommonConstant.COMMA);
                }
            }
        }

        String errMsgStr = sb.toString();
        return CommonUtil.handleString(errMsgStr);
    }
}
