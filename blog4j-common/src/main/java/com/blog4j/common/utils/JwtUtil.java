package com.blog4j.common.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.model.JwtUser;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : jwt相关工具类
 * @Create on : 2024/6/20 12:59
 **/
@Slf4j
public class JwtUtil {

    private static final String JWT_STR = "asdasdasifhueuiwyurfewbfjsdafjk";

    private static final int JWT_EXPIRE_TIME = 24;

    /**
     * 生成token
     *
     * @param payload 载荷
     * @return 返回token
     */
    public static String build(JwtUser payload) {
        JWTCreator.Builder jwt = JWT.create();
        jwt.withSubject(JSON.toJSONString(payload));
        jwt.withExpiresAt(DateUtil.offsetHour(new Date(), JWT_EXPIRE_TIME));
        return jwt.sign(Algorithm.HMAC256(JWT_STR));
    }

    /**
     * 获取token中payload
     *
     * @param jwt jwt转换为实体
     * @return 实体
     */
    public static JwtUser parse(String jwt) {
        try {
            DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256(JWT_STR)).build().verify(jwt);
            String subject = decodedJwt.getSubject();
            return JSON.parseObject(subject, JwtUser.class);
        } catch (Exception exception) {
            throw new Blog4jException("Parse jwt exception .");
        }
    }
}
