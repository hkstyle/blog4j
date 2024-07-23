package com.blog4j.article.controller;

import com.blog4j.article.entity.LabelEntity;
import com.blog4j.article.service.LabelService;
import com.blog4j.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/23 12:40
 **/
@RestController
@RequestMapping("/label")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LabelController {
    private final LabelService labelService;


}
