package com.blog4j.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.blog4j.api.client.FeignSystem;
import com.blog4j.common.constants.CommonConstant;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 13:05
 **/
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.endPoint}")
    private String endPoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.bucketDomain}")
    private String bucketDomain;

    @Autowired
    private FeignSystem feignSystem;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传后的路径
     */
    @Override
    public String upload(MultipartFile file) {
        // 上传文件所在目录名，当天上传的文件放到当天日期的目录下。
        String folderName = "Blog4j/" + DateFormatUtils.format(new Date(), CommonConstant.DATE_FORMAT);
        String fileName = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        // 从原始文件名中，获取文件扩展名
        String fileExtensionName = null;
        if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
            fileExtensionName = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf("."));
        }
        // 文件在 OSS 中存储的完整路径
        String filePath = folderName + "/" + fileName + fileExtensionName;
        OSS ossClient = null;
        try {
            // 获取 OSS 客户端实例
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 上传文件到OSS 并响应结果
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, filePath, file.getInputStream());
            ResponseMessage response = putObjectResult.getResponse();
            if(response == null) {
                // 返回上传文件的访问完整路径
                return "https://" + bucketDomain + "/" + filePath;
            } else {
                String errorMsg = "响应的错误状态码是【" + response.getStatusCode() +"】，" +
                        "错误信息【"+response.getErrorResponseAsString()+"】";
                log.error("文件上传失败，失败信息： [{}]", errorMsg);
                throw new Blog4jException(ErrorEnum.UPLOAD_FILE_ERROR);
            }
        } catch (Exception e) {
            throw new Blog4jException(ErrorEnum.UPLOAD_FILE_ERROR);
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    @Override
    public void delete(String filePath) {
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(bucketName, filePath);
        } catch (OSSException oe) {
            log.error("远程删除阿里云OSS文件失败, 错误码： [{}], 错误信息：[{}]",
                    oe.getErrorCode(), oe.getErrorMessage());
            throw new Blog4jException(ErrorEnum.UPLOAD_FILE_ERROR);
        } catch (ClientException ce) {
            log.error("远程删除阿里云OSS文件失败, 错误信息：[{}]", ce.getMessage());
            throw new Blog4jException(ErrorEnum.UPLOAD_FILE_ERROR);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 下载用户导入模板文件
     *
     * @return 文件存储路径
     */
    @Override
    public String downloadUserImportTemplate() {
        SystemBaseConfigVo baseSystemConfig = feignSystem.getBaseSystemConfig();
        // TODO 判断每个用户每天下载的次数
        return baseSystemConfig.getUserImportTemplatePath();
    }

    /**
     * 下载组织导入模板文件
     *
     * @return 文件存储路径
     */
    @Override
    public String downloadOrganizationImportTemplate() {
        // TODO 判断每个用户每天下载的次数
        SystemBaseConfigVo baseSystemConfig = feignSystem.getBaseSystemConfig();
        return baseSystemConfig.getOrganizationImportTemplatePath();
    }
}
