package com.blog4j.common.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : Rsa加密
 * @Create on : 2024/7/7 15:50
 **/
@Slf4j
public class RsaUtil {
    /**
     * 类型
     */
    public static final String ENCRYPT_TYPE = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 公钥加密
     *
     * @param content:
     * @author: 405
     * @date: 2021/6/28
     * @return: java.lang.String
     */
    public static String encrypt(String content) {
        try {
            RSA rsa = new RSA(null, getPublicKey());
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            log.error("公钥加密异常 msg:{}",e.getMessage());
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param content:
     * @author: 405
     * @date: 2021/6/28
     * @return: java.lang.String
     */
    public static String decrypt(String content) {
        try {
            RSA rsa = new RSA(getPrivateKey(), null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            log.error("私钥解密异常 msg:{}",e.getMessage());
        }
        return null;
    }

    /**
     * 获取公私钥
     *
     * @return 公私钥
     */
    public static Map<String,String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();

            // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
            String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
            String priEncBase64 = new BASE64Encoder().encode(priEncBytes);

            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY,pubEncBase64);
            map.put(PRIVATE_KEY,priEncBase64);

            return map;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getPublicKey() throws IOException {
        ClassPathResource resource = new ClassPathResource("publicKey.txt");
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    private static String getPrivateKey() throws IOException {
        ClassPathResource resource = new ClassPathResource("privateKey.txt");
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IOException {
        String a = "blog4j@test_123";
        String encrypt = encrypt(a);
        System.out.println("encrypt===" + encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println("decrypt===" + decrypt);
    }
}
