package com.blog4j.oss.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 13:04
 **/
public interface OssService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传后的路径
     */
    String upload(MultipartFile file);

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     */
    void delete(String filePath);

    /**
     * 下载用户导入模板文件
     *
     * @return 文件存储路径
     */
    String downloadUserImportTemplate();

    /**
     * 下载组织导入模板文件
     *
     * @return 文件存储路径
     */
    String downloadOrganizationImportTemplate();
}
