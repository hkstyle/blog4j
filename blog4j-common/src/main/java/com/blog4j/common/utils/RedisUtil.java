package com.blog4j.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2023/9/13 18:33
 **/
@SuppressWarnings("all")
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key 键
     * @param time 时间(秒)
     * @return 是否设置成功
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta 要增加几(大于0)
     */
    public void incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     */
    public void decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 删除指定的键
     *
     * @param key 键
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }
}

