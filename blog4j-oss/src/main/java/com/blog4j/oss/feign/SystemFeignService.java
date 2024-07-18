package com.blog4j.oss.feign;

import com.blog4j.common.model.FResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/18 12:25
 **/
@FeignClient("blog4j-system")
public interface SystemFeignService {
    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @GetMapping("/getBaseSystemConfig")
    FResult getBaseSystemConfig();
}
