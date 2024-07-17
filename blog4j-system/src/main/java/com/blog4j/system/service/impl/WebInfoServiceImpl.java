package com.blog4j.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.system.entity.WebInfoEntity;
import com.blog4j.system.mapper.WebInfoMapper;
import com.blog4j.system.service.WebInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/17 21:19
 **/
@Service
public class WebInfoServiceImpl extends ServiceImpl<WebInfoMapper, WebInfoEntity>
        implements WebInfoService {
}
