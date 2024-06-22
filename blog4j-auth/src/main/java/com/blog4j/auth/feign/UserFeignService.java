package com.blog4j.auth.feign;

import com.blog4j.common.model.FResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:33
 **/
@FeignClient("blog4j-user")
public interface UserFeignService {
    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/api/user/getUserInfoByUserName/{userName}")
    FResult getUserInfoByUserName(@PathVariable("userName") String userName);
}
