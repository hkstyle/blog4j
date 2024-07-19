package com.blog4j.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.api.vo.OssBaseConfigVo;
import com.blog4j.common.constants.CacheConstants;
import com.blog4j.system.entity.OssBaseConfigEntity;
import com.blog4j.system.mapper.OssBaseConfigMapper;
import com.blog4j.system.service.OssBaseConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 22:15
 **/
@Service
public class OssBaseConfigServiceImpl extends ServiceImpl<OssBaseConfigMapper, OssBaseConfigEntity>
        implements OssBaseConfigService {
    /**
     * 获取OSS基础配置信息
     *
     * @return OSS基础配置信息
     */
    @Cacheable(value = CacheConstants.SYSTEM, key = " 'ossBaseConfig' ")
    @Override
    public OssBaseConfigVo getOssBaseConfig() {
        OssBaseConfigEntity ossBaseConfig = this.baseMapper.selectOne(null);
        OssBaseConfigVo ossBaseConfigVo = new OssBaseConfigVo();
        BeanUtils.copyProperties(ossBaseConfig, ossBaseConfigVo);
        return ossBaseConfigVo;
    }
}
