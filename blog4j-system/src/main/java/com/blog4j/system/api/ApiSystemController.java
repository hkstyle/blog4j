package com.blog4j.system.api;

import com.blog4j.api.vo.OssBaseConfigVo;
import com.blog4j.api.vo.WebInfoVo;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.common.model.Result;
import com.blog4j.system.entity.WebInfoEntity;
import com.blog4j.system.service.OssBaseConfigService;
import com.blog4j.system.service.SystemService;
import com.blog4j.system.service.WebInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 12:57
 **/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ApiSystemController {
    private final SystemService systemService;
    private final WebInfoService webInfoService;
    private final OssBaseConfigService ossBaseConfigService;

    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @GetMapping("/getBaseSystemConfig")
    public Result getBaseSystemConfig() {
        SystemBaseConfigVo configVo = systemService.getBaseSystemConfig();
        return Result.ok(configVo);
    }

    /**
     * 获取网站配置信息
     *
     * @return 网站配置信息
     */
    @GetMapping("/getWebInfo")
    public Result getWebInfo() {
        WebInfoEntity webInfo = webInfoService.getOne(null);
        WebInfoVo webInfoVo = new WebInfoVo();
        BeanUtils.copyProperties(webInfo, webInfoVo);
        return Result.ok(webInfoVo);
    }

    /**
     * 获取OSS基础配置信息
     *
     * @return OSS基础配置信息
     */
    @GetMapping("/getOssBaseConfig")
    public Result getOssBaseConfig() {
        OssBaseConfigVo ossBaseConfigVo = ossBaseConfigService.getOssBaseConfig();
        return Result.ok(ossBaseConfigVo);
    }
}
