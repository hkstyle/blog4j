package com.blog4j.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.common.constants.CacheConstants;
import com.blog4j.system.entity.SystemEntity;
import com.blog4j.system.mapper.SystemMapper;
import com.blog4j.system.service.SystemService;
import com.blog4j.system.vo.req.SaveSystemReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 12:53
 **/
@Slf4j
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemEntity>
        implements SystemService {
    /**
     * 获取系统基础配置信息
     *
     * @return 系统基础配置信息
     */
    @Cacheable(value = CacheConstants.SYSTEM, key = " 'baseConfig' ")
    @Override
    public SystemBaseConfigVo getBaseSystemConfig() {
        SystemEntity system = this.baseMapper.selectOne(null);
        SystemBaseConfigVo configVo = new SystemBaseConfigVo();
        BeanUtils.copyProperties(system, configVo);
        return configVo;
    }

    /**
     * 保存系统配置信息
     *
     * @param reqVo 请求信息
     */
    @CacheEvict(value = CacheConstants.SYSTEM, key = " 'baseConfig' ")
    @Override
    public void saveBaseSystemConfig(SaveSystemReqVo reqVo) {
        SystemEntity system = new SystemEntity();
        BeanUtils.copyProperties(reqVo, system);
        this.baseMapper.updateById(system);
    }
}
