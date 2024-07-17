package com.blog4j.system.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 13:06
 **/
@Data
public class SaveSystemReqVo {
    @NotNull(message = "ID不能为空")
    private Integer id;

    /**
     * 是否开启短信功能
     */
    @NotNull(message = "是否开启短信功能不能为空")
    private Integer openSms;

    /**
     * 是否开启邮件功能
     */
    @NotNull(message = "是否开启邮件功能不能为空")
    private Integer openMsm;

    /**
     * 是否开启上传文件功能
     */
    @NotNull(message = "是否开启上传文件功能不能为空")
    private Integer openUpload;

    /**
     * 是否开启下载文件功能
     */
    @NotNull(message = "是否开启下载文件功能不能为空")
    private Integer openDownload;

    /**
     * 单个用户每天上传文件的次数
     */
    @NotNull(message = "单个用户每天上传文件的次数不能为空")
    private Integer uploadDayTimes;

    /**
     * 单个用户每天下载文件的次数
     */
    @NotNull(message = "单个用户每天下载文件的次数不能为空")
    private Integer downloadDayTimes;

    /**
     * 导入用户信息模板存储路径
     */
    @NotBlank(message = "导入用户信息模板存储路径不能为空")
    private String userImportTemplatePath;

    /**
     * 导入组织信息模板存储路径
     */
    @NotBlank(message = "导入组织信息模板存储路径不能为空")
    private String organizationImportTemplatePath;

    /**
     * 是否开启文本审核功能
     */
    @NotNull(message = "是否开启文本审核功能不能为空")
    private Integer openTextValid;

    /**
     * 是否开启图片审核功能
     */
    @NotNull(message = "是否开启图片审核功能不能为空")
    private Integer openImgValid;

    /**
     * 用户初始密码
     */
    @NotBlank(message = "用户初始密码不能为空")
    private String initPassword;

    /**
     * AES前后端加解密的KEY
     */
    @NotBlank(message = "AES前后端加解密的KEY不能为空")
    private String aesKey;

    /**
     * AES前后端加解密的IV
     */
    @NotBlank(message = "AES前后端加解密的IV不能为空")
    private String aesIv;

    /**
     * 单个组织最大容纳人数
     */
    @NotNull(message = "单个组织最大容纳人数不能为空")
    private Integer organizationMaxCapacity;

    /**
     * 单个用户允许加入的组织数
     */
    @NotNull(message = "单个用户允许加入的组织数不能为空")
    private Integer userJoinOrganizationMaxNum;
}
