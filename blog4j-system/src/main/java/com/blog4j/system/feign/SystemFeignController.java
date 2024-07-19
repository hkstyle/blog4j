package com.blog4j.system.feign;

import com.blog4j.api.client.FeignSystem;
import com.blog4j.api.vo.OssBaseConfigVo;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.api.vo.WebInfoVo;
import com.blog4j.system.entity.OssBaseConfigEntity;
import com.blog4j.system.entity.SystemEntity;
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
 * @Create on : 2024/7/18 21:29
 **/
@RestController
@RequestMapping("/feign")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SystemFeignController implements FeignSystem {
    private final SystemService systemService;
    private final WebInfoService webInfoService;
    private final OssBaseConfigService ossBaseConfigService;

    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @Override
    @GetMapping("/getBaseSystemConfig")
    public SystemBaseConfigVo getBaseSystemConfig() {
        SystemEntity system = systemService.getOne(null);
        SystemBaseConfigVo configVo = new SystemBaseConfigVo();
        BeanUtils.copyProperties(system, configVo);
        return configVo;
    }

    /**
     * 获取网站配置信息
     *
     * @return 网站配置信息
     */
    @GetMapping("/getWebInfo")
    public WebInfoVo getWebInfo() {
        WebInfoEntity webInfo = webInfoService.getOne(null);
        WebInfoVo webInfoVo = new WebInfoVo();
        BeanUtils.copyProperties(webInfo, webInfoVo);
        return webInfoVo;
    }

    /**
     * 获取OSS基础配置信息
     *
     * @return OSS基础配置信息
     */
    @GetMapping("/getOssBaseConfig")
    public OssBaseConfigVo getOssBaseConfig() {
        OssBaseConfigEntity ossBaseConfig = ossBaseConfigService.getOne(null);
        OssBaseConfigVo ossBaseConfigVo = new OssBaseConfigVo();
        BeanUtils.copyProperties(ossBaseConfig, ossBaseConfigVo);
        return ossBaseConfigVo;
    }
}
