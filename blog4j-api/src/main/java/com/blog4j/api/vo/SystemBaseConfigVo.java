package com.blog4j.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 12:31
 **/
@Data
public class SystemBaseConfigVo implements Serializable {
    private Integer id;

    /**
     * 是否开启短信功能
     */
    private Integer openSms;

    /**
     * 是否开启邮件功能
     */
    private Integer openMsm;

    /**
     * 是否开启上传文件功能
     */
    private Integer openUpload;

    /**
     * 是否开启下载文件功能
     */
    private Integer openDownload;

    /**
     * 单个用户每天上传文件的次数
     */
    private Integer uploadDayTimes;

    /**
     * 单个用户每天下载文件的次数
     */
    private Integer downloadDayTimes;

    /**
     * 导入用户信息模板存储路径
     */
    private String userImportTemplatePath;

    /**
     * 导入组织信息模板存储路径
     */
    private String organizationImportTemplatePath;

    /**
     * 是否开启文本审核功能
     */
    private Integer openTextValid;

    /**
     * 是否开启图片审核功能
     */
    private Integer openImgValid;

    /**
     * 用户初始密码
     */
    private String initPassword;

    /**
     * AES前后端加解密的KEY
     */
    private String aesKey;

    /**
     * AES前后端加解密的IV
     */
    private String aesIv;

    /**
     * 单个组织最大容纳人数
     */
    private Integer organizationMaxCapacity;

    /**
     * 单个用户允许加入的组织数
     */
    private Integer userJoinOrganizationMaxNum;

    /**
     * 用户默认头像
     */
    private String userDefaultAvatar;
}
