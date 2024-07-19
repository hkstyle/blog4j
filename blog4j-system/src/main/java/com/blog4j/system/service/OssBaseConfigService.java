package com.blog4j.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.api.vo.OssBaseConfigVo;
import com.blog4j.system.entity.OssBaseConfigEntity;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 22:15
 **/
public interface OssBaseConfigService extends IService<OssBaseConfigEntity> {
    /**
     * 获取OSS基础配置信息
     *
     * @return OSS基础配置信息
     */
    OssBaseConfigVo getOssBaseConfig();
}
