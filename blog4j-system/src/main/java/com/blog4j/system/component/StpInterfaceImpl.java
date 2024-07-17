package com.blog4j.system.component;

import cn.dev33.satoken.stp.StpInterface;
import com.blog4j.common.model.FResult;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.vo.PermissionVo;
import com.blog4j.common.vo.UserInfoVo;
import com.blog4j.system.feign.UserFeignService;
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
        FResult result = userFeignService.getPermissionListByUserId((String) userId);
        List<PermissionVo> list = CommonUtil.getPermissionListByUserId(result);
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
        FResult result = userFeignService.getUserInfoByUserId((String)userId);
        UserInfoVo userInfoVo = CommonUtil.getUserInfo(result);
        List<String> list = new ArrayList<>();
        list.add(userInfoVo.getRoleCode());
        return list;
    }
}
