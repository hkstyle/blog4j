package com.blog4j.common.constants;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 12:36
 **/
public class CacheConstants {
    /**
     * 系统服务缓存名
     */
    public static final String SYSTEM = "System";

    /**
     * 冒号
     */
    public static final String SPLIT = "::";

    /**
     * 系统基础配置的缓存名
     */
    public static final String SYSTEM_BASE_CONFIG_KEY = SYSTEM + SPLIT + "baseConfig";

    /**
     * OSS基础配置的缓存名
     */
    public static final String SYSTEM_OSS_BASE_CONFIG_KEY = SYSTEM + SPLIT + "ossBaseConfig";
}
