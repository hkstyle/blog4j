package com.blog4j.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 唯一ID生成工具
 * @Create on : 2024/6/26 19:58
 **/
@Slf4j
public class IdGeneratorSnowflakeUtil {
    private static long workerId = 0;  // 第几号机房
    private static long datacenterId = 1;  //第几号机器
    private static final Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init(){
        try{
            //获取本机的ip地址编码
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId: " + workerId);
        }catch (Exception exception){
            exception.printStackTrace();
            log.warn("当前机器的workerId获取失败 ----> " + exception);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized static String snowflakeId(){
        return String.valueOf(snowflake.nextId());
    }

    public synchronized static String snowflakeId(long workerId, long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return String.valueOf(snowflake.nextId());
    }
}
