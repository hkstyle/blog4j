package com.blog4j.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.blog4j.common.model.Result;
import com.blog4j.system.entity.SystemEntity;
import com.blog4j.system.service.SystemService;
import com.blog4j.system.vo.req.SaveSystemReqVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 12:50
 **/
@RestController
@RequestMapping("/")
public class SystemController {
    @Autowired
    private SystemService systemService;

    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @SaCheckLogin
    @GetMapping("/getBaseSystemConfig")
    public Result getBaseSystemConfig() {
        SystemEntity system = systemService.getOne(null);
        return Result.ok(system);
    }

    /**
     * 保存系统配置信息
     *
     * @param reqVo 请求信息
     * @return 保存成功
     */
    @PostMapping("/save")
    public Result save(@RequestBody @Valid SaveSystemReqVo reqVo) {
        SystemEntity system = new SystemEntity();
        BeanUtils.copyProperties(reqVo, system);
        systemService.updateById(system);
        return Result.ok();
    }
}
