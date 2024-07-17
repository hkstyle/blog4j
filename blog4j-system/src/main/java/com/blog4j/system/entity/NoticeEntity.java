package com.blog4j.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 系统公告信息
 * @Create on : 2024/7/17 13:20
 **/
@Data
@TableName("t_system")
public class NoticeEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告版本
     */
    private String noticeVersion;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告创建人ID
     */
    private String creater;

    /**
     * 公告创建人名称
     */
    private String createrName;

    /**
     * 公告更新人ID
     */
    private String updater;

    /**
     * 公告更新人名称
     */
    private String updaterName;

    /**
     * 公告类型(1:系统公告  2:组织公告)
     */
    private Integer noticeType;

    /**
     * 审批状态(1:待审批  2: 审批通过  3:审批拒绝)
     */
    private Integer approveStatus;

    /**
     * 审批时间
     */
    private String approveTime;

    /**
     * 审批留言
     */
    private String approveMessage;

    /**
     * 公告状态(1:待发布  2:已发布 )
     */
    private Integer noticeStatus;

    /**
     * 是否已被删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;
}
