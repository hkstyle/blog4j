package com.blog4j.auth.service.impl;

import com.blog4j.auth.service.CaptchaService;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.code.kaptcha.Producer;

import java.awt.image.BufferedImage;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 20:19
 **/
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private static final int CAPTCHA_EXPIRE_TIME = 360;

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成图片验证码
     *
     * @param uuid uuid
     * @return 图片验证码
     */
    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            log.error("uuid is blank .");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        // 生成文字验证码
        String code = producer.createText();

        // 存入redis, 有效期360秒
        redisUtil.set(uuid, code, CAPTCHA_EXPIRE_TIME);

        return producer.createImage(code);
    }
}
