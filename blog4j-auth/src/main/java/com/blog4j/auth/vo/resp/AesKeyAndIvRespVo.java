package com.blog4j.auth.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 17:24
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AesKeyAndIvRespVo {
    /**
     * AES前后端加解密的KEY
     */
    private String key;

    /**
     * AES前后端加解密的IV
     */
    private String iv;
}
