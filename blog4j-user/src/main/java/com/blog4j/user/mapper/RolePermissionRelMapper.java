package com.blog4j.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog4j.user.entity.RolePermissionRelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 00:28
 **/
@Mapper
public interface RolePermissionRelMapper extends BaseMapper<RolePermissionRelEntity> {
    /**
     * 批量添加角色与权限的关系
     *
     * @param roleId 角色ID
     * @param permissionIds 权限ID集合
     */
    void batchSave(@Param("roleId") String roleId, @Param("permissionIds") List<Integer> permissionIds);
}
