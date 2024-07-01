package com.blog4j.article.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/1 12:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleStatusRespVo {
    private Integer code;

    private String desc;
}
