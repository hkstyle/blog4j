package com.blog4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/19 22:08
 **/
@Data
@TableName(value = "t_oss_extend_config")
public class OssExtendConfigEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 允许上传的图片大小
     */
    private Integer imgSize;

    /**
     * 允许上传的文件大小
     */
    private Integer fileSize;

    /**
     * 允许上传的图片类型
     */
    private String imgType;

    /**
     * 允许上传的图片类型
     */
    private String fileType;

    /**
     * 图片在OSS里面存储的目录
     */
    private String imgStorageDirectory;

    /**
     * 文件在OSS里面存储的目录
     */
    private String fileStorageDirectory;
}
