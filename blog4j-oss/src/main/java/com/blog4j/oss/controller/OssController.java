package com.blog4j.oss.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.blog4j.common.model.Result;
import com.blog4j.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 13:04
 **/
@RequestMapping("/file")
@RestController
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传后的路径
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER"}, mode = SaMode.OR)
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String path = ossService.upload(file);
        return Result.ok(path);
    }
}
