package com.blog4j.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 角色信息 访客完善用户信息成为普通用户
 * 普通用户提交申请成为创作者
 * 普通用户提交加入组织的神情成员某个组织的成员
 * @Create on : 2024/6/27 22:25
 **/
@Getter
@AllArgsConstructor
public enum RecordTimesEnum {
    /**
     * 用户上传次数
     */
    USER_UPLOAD_TIMES(1, "用户上传次数"),

    /**
     * 用户下载次数
     */
    USER_DOWNLOAD_TIMES(2, "用户下载次数"),
    ;

    private final Integer code;
    private final String desc;

}
