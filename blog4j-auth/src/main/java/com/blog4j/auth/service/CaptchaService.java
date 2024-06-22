package com.blog4j.auth.service;

import java.awt.image.BufferedImage;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 20:19
 **/
public interface CaptchaService {
    /**
     * 生成图片验证码
     *
     * @param uuid uuid
     * @return 图片验证码
     */
    BufferedImage getCaptcha(String uuid);
}
