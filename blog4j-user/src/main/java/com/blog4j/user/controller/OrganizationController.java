package com.blog4j.user.controller;

import com.blog4j.common.model.Result;
import com.blog4j.user.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/8 12:58
 **/
@RequestMapping("/organization")
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    /**
     * 获取所有的组织信息
     *
     * @return 组织信息
     */
    @GetMapping("/list")
    public Result list() {
        return Result.ok(organizationService.list());
    }
}
