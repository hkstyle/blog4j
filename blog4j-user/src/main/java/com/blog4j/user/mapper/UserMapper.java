package com.blog4j.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.vo.resp.UserListRespVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:15
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 查询用户列表
     *
     * @param userName 用户名称
     * @param status 用户状态
     * @param organizationId 组织ID
     * @return 用户列表
     */
    List<UserListRespVo> userList(@Param("userName") String userName,
                                  @Param("status") Integer status,
                                  @Param("organizationId") String organizationId);

    /**
     * 批量保存用户信息
     *
     * @param userList 用户信息集合
     */
    void batchInsert(@Param("userList") List<UserEntity> userList);
}
