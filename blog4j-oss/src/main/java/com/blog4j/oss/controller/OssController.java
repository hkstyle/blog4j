package com.blog4j.oss.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.blog4j.common.model.Result;
import com.blog4j.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/2 13:04
 **/
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OssController {
    private final OssService ossService;

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

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 删除成功
     */
    @SaCheckRole(value = {"SUPER_ADMIN", "ORGANIZATION_ADMIN", "COMPOSER"}, mode = SaMode.OR)
    @GetMapping("/delete")
    public Result delete(@RequestParam String filePath) {
        ossService.delete(filePath);
        return Result.ok();
    }

    /**
     * 下载用户导入模板文件
     *
     * @return 文件存储路径
     */
    @SaCheckLogin
    @GetMapping("/downloadUserImportTemplate")
    public Result downloadUserImportTemplate() {
        String path = ossService.downloadUserImportTemplate();
        return Result.ok(path);
    }

    /**
     * 下载组织导入模板文件
     *
     * @return 文件存储路径
     */
    @SaCheckLogin
    @GetMapping("/downloadOrganizationImportTemplate")
    public Result downloadOrganizationImportTemplate() {
        String path = ossService.downloadOrganizationImportTemplate();
        return Result.ok(path);
    }
}
