package com.blog4j.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.system.entity.SystemEntity;
import com.blog4j.system.mapper.SystemMapper;
import com.blog4j.system.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 12:53
 **/
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemEntity>
        implements SystemService {
}
