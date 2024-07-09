package com.blog4j.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/9 12:44
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteUserArticleVo {
    /**
     * 用户集合
     */
    @NotEmpty(message = "用户ID不能为空")
    private List<String> userIds;
}
