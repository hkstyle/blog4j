package com.blog4j.api.client;

import com.blog4j.api.vo.WebInfoVo;
import com.blog4j.api.vo.SystemBaseConfigVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 13:31
 **/
@FeignClient(value = "blog4j-system", path = "/feign")
public interface FeignSystem {
    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @GetMapping("/getBaseSystemConfig")
    SystemBaseConfigVo getBaseSystemConfig();

    /**
     * 获取网站配置信息
     *
     * @return 网站配置信息
     */
    @GetMapping("/getWebInfo")
    WebInfoVo getWebInfo();
}
