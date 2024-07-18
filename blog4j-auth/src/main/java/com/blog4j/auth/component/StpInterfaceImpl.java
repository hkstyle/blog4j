package com.blog4j.auth.component;

import cn.dev33.satoken.stp.StpInterface;
import com.blog4j.api.client.FeignUser;
import com.blog4j.api.vo.RoleInfoVo;
import com.blog4j.api.vo.PermissionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 自定义权限加载接口实现类
 * @Create on : 2024/6/21 19:30
 **/
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private FeignUser feignUser;

    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @param loginType 登录类型
     * @return 用户权限列表
     */
    @Override
    public List<String> getPermissionList(Object userId, String loginType) {
        List<PermissionVo> list = feignUser.getPermissionListByUserId((String) userId);
        return list.stream().map(PermissionVo::getPermissionCode).collect(Collectors.toList());
    }

    /**
     * 获取用户角色列表
     *
     * @param userId 用户ID
     * @param loginType 登录类型
     * @return 用户角色列表
     */
    @Override
    public List<String> getRoleList(Object userId, String loginType) {
        RoleInfoVo roleInfoVo = feignUser.getRoleInfoByUserId((String) userId);
        List<String> list = new ArrayList<>();
        list.add(roleInfoVo.getRoleCode());
        return list;
    }
}
