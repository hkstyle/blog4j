package com.blog4j.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 22:21
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class OssBaseConfigVo {
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
