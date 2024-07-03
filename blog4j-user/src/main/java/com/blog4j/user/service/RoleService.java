package com.blog4j.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog4j.common.vo.RoleInfoVo;
import com.blog4j.user.entity.RoleEntity;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/26 13:22
 **/
public interface RoleService extends IService<RoleEntity>  {
    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return 角色信息
     */
    RoleInfoVo getRoleInfoByUserId(String userId);
}
