package com.blog4j.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.system.entity.NoticeEntity;
import com.blog4j.system.mapper.NoticeMapper;
import com.blog4j.system.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/20 08:58
 **/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, NoticeEntity>
        implements NoticeService {
}
