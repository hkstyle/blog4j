package com.blog4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 22:12
 **/
@Data
@TableName(value = "t_oss_base_config")
public class OssBaseConfigEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * endPoint
     */
    private String endPoint;

    /**
     * bucketName
     */
    private String bucketName;

    /**
     * bucketDomain
     */
    private String bucketDomain;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;
}
