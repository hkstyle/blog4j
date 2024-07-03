package com.blog4j.article.component;

import cn.dev33.satoken.stp.StpInterface;
import com.blog4j.article.feign.UserFeignService;
import com.blog4j.common.model.FResult;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserFeignService userFeignService;

    /**
     * 获取用户权限列表
     *
     * @param userId 用户ID
     * @param loginType 登录类型
     * @return 用户权限列表
     */
    @Override
    public List<String> getPermissionList(Object userId, String loginType) {
        List<String> list = new ArrayList<>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        return list;
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
        FResult result = userFeignService.getUserInfoByUserId((String)userId);
        UserInfoVo userInfoVo = CommonUtil.getUserInfo(result);
        List<String> list = new ArrayList<>();
        list.add(userInfoVo.getRoleCode());
        return list;
    }
}
