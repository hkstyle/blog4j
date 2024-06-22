package com.blog4j.auth.component;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 自定义权限加载接口实现类
 * @Create on : 2024/6/21 19:30
 **/
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> list = new ArrayList<>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        return list;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
