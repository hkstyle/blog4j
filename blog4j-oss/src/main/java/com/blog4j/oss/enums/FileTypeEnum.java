package com.blog4j.oss.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 上传的文件类型
 * @Create on : 2024/7/2 13:33
 **/
@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    IMAGE(1, "image"),
    WORD(2, "word"),
    EXCEL(3, "excel"),
    PDF(4, "pdf"),
    OTHER_FILE(5, "other"),
    ;

    private final int fileType;

    private final String fileDocumentName;
}
