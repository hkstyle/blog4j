package com.blog4j.common.utils;

import com.alibaba.fastjson.TypeReference;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.FResult;
import com.blog4j.common.vo.OrganizationVo;
import com.blog4j.common.vo.PermissionVo;
import com.blog4j.common.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
     * 获取用户信息
     *
     * @param result 待解析的结果
     * @return 用户信息
     */
    public static UserInfoVo getUserInfo(FResult result) {
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            log.error("远程调用user模块获取用户角色信息失败, 失败原因：[{}]", message);
            throw new Blog4jException(code, message);
        }

        return result.getData(new TypeReference<UserInfoVo>() {
        });
    }

    /**
     * 根据组织管理员ID获取该组织下所有的用户ID列表
     *
     * @param result 待解析的结果
     * @return 该组织下所有的用户ID列表
     */
    public static List<String> getUserIdsByOrganizationAdmin(FResult result) {
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            log.error("远程调用user模块, 根据组织管理员ID获取该组织下所有的用户ID列表失败, 失败原因：[{}]", message);
            throw new Blog4jException(code, message);
        }

        return result.getData(new TypeReference<List<String>>() {
        });
    }

    /**
     * 根据用户ID获取组织信息
     *
     * @param result 待解析的结果
     * @return 组织信息
     */
    public static List<OrganizationVo> getOrganizationInfoByUserId(FResult result) {
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            log.error("远程调用user模块, 根据用户ID获取组织信息失败, 失败原因：[{}]", message);
            throw new Blog4jException(code, message);
        }

        return result.getData(new TypeReference<List<OrganizationVo>>() {
        });
    }

    /**
     * 根据用户ID获取权限信息
     *
     * @param result 待解析的结果
     * @return 组织信息
     */
    public static List<PermissionVo> getPermissionListByUserId(FResult result) {
        Integer code = result.getCode();
        String message = result.getMessage();
        if (code != CommonConstant.SUCCESS_CODE) {
            log.error("远程调用user模块, 根据用户ID获取权限信息失败, 失败原因：[{}]", message);
            throw new Blog4jException(code, message);
        }

        return result.getData(new TypeReference<List<PermissionVo>>() {
        });
    }
}
