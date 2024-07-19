package com.blog4j.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.system.entity.SystemEntity;
import com.blog4j.system.vo.req.SaveSystemReqVo;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 12:53
 **/
public interface SystemService extends IService<SystemEntity> {
    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    SystemBaseConfigVo getBaseSystemConfig();

    /**
     * 保存系统配置信息
     *
     * @param reqVo 请求信息
     */
    void saveBaseSystemConfig(SaveSystemReqVo reqVo);
}
