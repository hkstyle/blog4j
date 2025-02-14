package com.blog4j.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.api.client.FeignArticle;
import com.blog4j.api.client.FeignSystem;
import com.blog4j.api.vo.SystemBaseConfigVo;
import com.blog4j.api.vo.UserInfoVo;
import com.blog4j.common.constants.CacheConstants;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.RoleEnum;
import com.blog4j.common.enums.UserSexEnum;
import com.blog4j.common.enums.UserStatusEnum;
import com.blog4j.common.enums.YesOrNoEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.ExcelUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.common.utils.RedisUtil;
import com.blog4j.common.utils.RsaUtil;
import com.blog4j.common.utils.ValidateUtil;
import com.blog4j.api.vo.DeleteUserArticleVo;
import com.blog4j.user.entity.OrganizationUserRelEntity;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.UserEntity;
import com.blog4j.user.listener.ImportUserExcelListener;
import com.blog4j.user.mapper.OrganizationUserRelMapper;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.UserMapper;
import com.blog4j.user.model.UserExcel;
import com.blog4j.user.service.UserService;
import com.blog4j.user.vo.req.BatchCreateUserReqVo;
import com.blog4j.user.vo.req.CreateUserReqVo;
import com.blog4j.user.vo.req.DeleteUserReqVo;
import com.blog4j.api.vo.EditUserLastLoginTimeReqVo;
import com.blog4j.user.vo.req.EditUserReqVo;
import com.blog4j.user.vo.req.ExportUserReqVo;
import com.blog4j.user.vo.req.UserListReqVo;
import com.blog4j.user.vo.resp.UserListRespVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/22 13:16
 **/
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private final RoleMapper roleMapper;
    private final OrganizationUserRelMapper organizationUserRelMapper;
    private final FeignArticle feignArticle;
    private final ExcelUtil excelUtil;
    private final UserMapper userMapper;
    private final FeignSystem feignSystem;
    private final RedisUtil redisUtil;
    private final ObjectMapper objectMapper;

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserInfoVo getUserInfoByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            log.error("UserName is blank .");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUserName, userName);
        wrapper.ne(UserEntity::getStatus, UserStatusEnum.LOCK.getCode());
        UserEntity userEntity = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(userEntity)) {
            log.error("User is empty .");
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userEntity, userInfoVo);
        return userInfoVo;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public UserInfoVo getUserInfoByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            log.error("userId is blank .");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserId, userId);
        queryWrapper.ne(UserEntity::getStatus, UserStatusEnum.LOCK.getCode());
        UserEntity userEntity = this.baseMapper.selectOne(queryWrapper);
        if (Objects.isNull(userEntity)) {
            log.error("User is empty .");
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        RoleEntity roleEntity = roleMapper.selectById(userEntity.getRoleId());
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userEntity, userInfoVo);
        userInfoVo.setRoleCode(roleEntity.getRoleCode())
                .setRoleName(roleEntity.getRoleName())
                .setRoleId(userEntity.getRoleId());
        return userInfoVo;
    }

    /**
     * 查询用户列表
     *
     * @param reqVo 查询条件
     * @return 用户列表
     */
    @Override
    public List<UserListRespVo> userList(UserListReqVo reqVo) {
        PageHelper.startPage(reqVo.getPageNo(), reqVo.getPageSize());
        return this.baseMapper.userList(reqVo.getUserName(), reqVo.getStatus(), reqVo.getOrganizationId());
    }

    /**
     * 查询组织用户
     *
     * @param reqVo 查询条件
     * @return 组织用户
     */
    @Override
    public List<UserListRespVo> organizationUserList(UserListReqVo reqVo) {
        if (StringUtils.isBlank(reqVo.getOrganizationId())) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        PageHelper.startPage(reqVo.getPageNo(), reqVo.getPageSize());
        return this.baseMapper.userList(reqVo.getUserName(), reqVo.getStatus(), reqVo.getOrganizationId());
    }

    /**
     * 创建用户信息
     *
     * @param reqVo 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(CreateUserReqVo reqVo) {
        this.baseMapper.insert(this.createUser(reqVo));
    }

    private UserEntity createUser(CreateUserReqVo reqVo) {
        this.beforeCreate(reqVo);
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(reqVo, user);
        String userId = IdGeneratorSnowflakeUtil.snowflakeId();
        SystemBaseConfigVo systemBaseConfig = this.getSystemBaseConfig();
        user.setUserId(userId)
                .setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCreateTime(CommonUtil.getCurrentDateTime())
                .setPassword(RsaUtil.encrypt(systemBaseConfig.getInitPassword()))
                .setDeleted(YesOrNoEnum.NO.getCode())
                .setStatus(UserStatusEnum.NORMAL.getCode());
        if (StringUtils.isBlank(reqVo.getRoleId())) {
            LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<RoleEntity>()
                    .eq(RoleEntity::getRoleCode, RoleEnum.VISITOR.getDesc());
            RoleEntity role = roleMapper.selectOne(wrapper);
            if (Objects.isNull(role)) {
                throw new Blog4jException(ErrorEnum.SYSTEM_ERROR);
            }
            user.setRoleId(role.getRoleId());
        }

        if (StringUtils.isBlank(reqVo.getAvatar())) {
            user.setAvatar(systemBaseConfig.getUserDefaultAvatar());
        }
        return user;
    }

    /**
     * 编辑用户信息
     *
     * @param reqVo 用户信息
     */
    @Override
    public void edit(EditUserReqVo reqVo) {
        UserEntity user = this.beforeUpdate(reqVo);
        BeanUtils.copyProperties(reqVo, user);
        user.setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(user);
    }

    /**
     * 删除用户
     *
     * @param reqVo 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(DeleteUserReqVo reqVo) {
        this.beforeDelete(reqVo);
        // 删除用户名下的文章
        this.deleteUserArticle(reqVo);
        // 删除用户的组织关系
        this.deleteOrganizationRel(reqVo);
        this.baseMapper.deleteBatchIds(reqVo.getUserIds());
    }

    /**
     * 更新用户的最近一次登录时间
     *
     * @param reqVo 请求信息
     */
    @Override
    public void updateUserLastLoginTime(EditUserLastLoginTimeReqVo reqVo) {
        String userId = reqVo.getUserId();
        String lastLoginTime = reqVo.getLastLoginTime();
        UserEntity user = this.baseMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }
        user.setLastLoginTime(lastLoginTime);
        this.baseMapper.updateById(user);
    }

    /**
     * 用户批量导入
     *
     * @param multipartFile 文件
     * @return 解析之后的用户信息
     */
    @Override
    public List<UserExcel> importUser(MultipartFile multipartFile) {
        List<UserExcel> dataList = new ArrayList<>();
        try {
            File file = CommonUtil.convertMultipartFileToFile(multipartFile);
            InputStream inputStream = Files.newInputStream(file.toPath());
            dataList = EasyExcel.read(inputStream, UserExcel.class, new ImportUserExcelListener(dataList, userMapper))
                    .headRowNumber(1)
                    .sheet(0)
                    .doReadSync();
        } catch (IOException ioException) {
            throw new Blog4jException(ErrorEnum.IO_ERROR);
        }
        return dataList;
    }

    /**
     * 批量创建用户信息
     *
     * @param reqVo 用户信息
     */
    @Override
    public void batchCreate(BatchCreateUserReqVo reqVo) {
        List<CreateUserReqVo> list = reqVo.getList();
        List<UserEntity> userList = list.stream().map(item -> {
            CreateUserReqVo userReqVo = new CreateUserReqVo();
            BeanUtils.copyProperties(item, userReqVo);
            return this.createUser(userReqVo);
        }).collect(Collectors.toList());
        this.baseMapper.batchInsert(userList);
    }

    /**
     * 用户导出
     *
     * @param exportUserReqVo 用户ID
     */
    @Override
    public void exportUser(ExportUserReqVo exportUserReqVo, HttpServletResponse response) {
        List<UserEntity> userList = this.baseMapper.selectBatchIds(exportUserReqVo.getUserIds());
        if (CollectionUtil.isEmpty(userList)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        List<UserExcel> userExcelList = userList.stream().map(item -> {
            UserExcel userExcel = new UserExcel();
            BeanUtils.copyProperties(item, userExcel);
            return userExcel;
        }).collect(Collectors.toList());
        String path = excelUtil.export(userExcelList, UserExcel.class, "用户信息");
        try {
            ExcelUtil.exportExcel(response, path);
        } catch (Exception exception) {
            throw new Blog4jException(ErrorEnum.EXPORT_USER_ERROR);
        }
    }

    private void deleteUserArticle(DeleteUserReqVo reqVo) {
        List<String> userIds = reqVo.getUserIds();
        feignArticle.deleteUserArticle(DeleteUserArticleVo.builder().userIds(userIds).build());
    }

    private void deleteOrganizationRel(DeleteUserReqVo reqVo) {
        List<String> userIds = reqVo.getUserIds();
        LambdaQueryWrapper<OrganizationUserRelEntity> wrapper = new LambdaQueryWrapper<OrganizationUserRelEntity>()
                .in(OrganizationUserRelEntity::getUserId, userIds);
        List<OrganizationUserRelEntity> organizationUserRelList
                = organizationUserRelMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(organizationUserRelList)) {
            return;
        }

        Set<Integer> ids = organizationUserRelList.stream()
                .map(OrganizationUserRelEntity::getId).collect(Collectors.toSet());
        organizationUserRelMapper.deleteBatchIds(ids);
    }

    private void beforeDelete(DeleteUserReqVo reqVo) {
        String currentLoginUserId = StpUtil.getLoginIdAsString();
        List<String> userIds = reqVo.getUserIds();
        if (userIds.contains(currentLoginUserId)) {
            throw new Blog4jException(ErrorEnum.DELETE_CURRENT_LOGIN_ERROR);
        }

        List<UserEntity> userList = this.baseMapper.selectBatchIds(userIds);
        if (CollectionUtil.size(userList) != CollectionUtil.size(userIds)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        // 不能删除超级管理员和组织管理员
        Set<String> roleIdSet = userList.stream().map(UserEntity::getRoleId).collect(Collectors.toSet());
        List<RoleEntity> roleList = roleMapper.selectBatchIds(roleIdSet);
        if (CollectionUtil.isNotEmpty(roleList)) {
            List<RoleEntity> list = roleList.stream().filter(role -> StringUtils.equals(role.getRoleCode(), RoleEnum.ORGANIZATION_ADMIN.getDesc()) ||
                    StringUtils.equals(role.getRoleCode(), RoleEnum.SUPER_ADMIN.getDesc())).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(list)) {
                throw new Blog4jException(ErrorEnum.DELETE_ORGANIZATION_ADMIN_ERROR);
            }
        }
    }

    private UserEntity beforeUpdate(EditUserReqVo reqVo) {
        // 用户ID是否存在
        String userId = reqVo.getUserId();
        UserEntity user = this.baseMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw new Blog4jException(ErrorEnum.USER_NOT_EXIST_ERROR);
        }

        // 用户名称不能重复
        LambdaQueryWrapper<UserEntity> wrapper1 = new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getUserName, reqVo.getUserName())
                .ne(UserEntity::getUserId, userId);
        UserEntity val1 = this.baseMapper.selectOne(wrapper1);
        if (Objects.nonNull(val1)) {
            throw new Blog4jException(ErrorEnum.USERNAME_REPEAT_ERROR);
        }

        // 用户手机号码不能重复
        if (StringUtils.isNotBlank(reqVo.getPhone())) {
            LambdaQueryWrapper<UserEntity> wrapper2 = new LambdaQueryWrapper<UserEntity>()
                    .eq(UserEntity::getPhone, reqVo.getPhone())
                    .ne(UserEntity::getUserId, userId);
            UserEntity val2 = this.baseMapper.selectOne(wrapper2);
            if (Objects.nonNull(val2)) {
                throw new Blog4jException(ErrorEnum.PHONE_REPEAT_ERROR);
            }
        }

        // 角色是否合法
        if (StringUtils.isNotBlank(reqVo.getRoleId())) {
            RoleEntity role = roleMapper.selectById(reqVo.getRoleId());
            if (Objects.isNull(role)) {
                throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
            }
        }

        if (StringUtils.isNotBlank(reqVo.getPhone()) && !ValidateUtil.isValidMobile(reqVo.getPhone())) {
            throw new Blog4jException(ErrorEnum.PHONE_ERROR);
        }

        Integer sex = reqVo.getSex();
        if (Objects.nonNull(sex)) {
            if (!Objects.equals(sex, UserSexEnum.MAN.getCode()) &&
                    !Objects.equals(sex, UserSexEnum.WOMAN.getCode()) &&
                    !Objects.equals(sex, UserSexEnum.SECRET.getCode())) {
                throw new Blog4jException(ErrorEnum.SEX_ERROR);
            }
        }

        return user;
    }

    private void beforeCreate(CreateUserReqVo reqVo) {
        // 用户名称不能重复
        LambdaQueryWrapper<UserEntity> wrapper1 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getUserName, reqVo.getUserName());
        List<UserEntity> val1 = this.baseMapper.selectList(wrapper1);
        if (CollectionUtil.isNotEmpty(val1)) {
            throw new Blog4jException(ErrorEnum.USERNAME_REPEAT_ERROR);
        }

        // 用户手机号码不能重复
        if (StringUtils.isNotBlank(reqVo.getPhone())) {
            LambdaQueryWrapper<UserEntity> wrapper2 = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getPhone, reqVo.getPhone());
            List<UserEntity> val2 = this.baseMapper.selectList(wrapper2);
            if (CollectionUtil.isNotEmpty(val2)) {
                throw new Blog4jException(ErrorEnum.PHONE_REPEAT_ERROR);
            }
        }

        // 角色是否合法
        if (StringUtils.isNotBlank(reqVo.getRoleId())) {
            RoleEntity role = roleMapper.selectById(reqVo.getRoleId());
            if (Objects.isNull(role)) {
                throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
            }
        }

        if (StringUtils.isNotBlank(reqVo.getPhone()) && !ValidateUtil.isValidMobile(reqVo.getPhone())) {
            throw new Blog4jException(ErrorEnum.PHONE_ERROR);
        }

        Integer sex = reqVo.getSex();
        if (Objects.nonNull(sex)) {
            if (!Objects.equals(sex, UserSexEnum.MAN.getCode()) &&
                    !Objects.equals(sex, UserSexEnum.WOMAN.getCode()) &&
                    !Objects.equals(sex, UserSexEnum.SECRET.getCode())) {
                throw new Blog4jException(ErrorEnum.SEX_ERROR);
            }
        }
    }

    private SystemBaseConfigVo getSystemBaseConfig()  {
        Object val = redisUtil.get(CacheConstants.SYSTEM_BASE_CONFIG_KEY);
        if (Objects.isNull(val)) {
            return feignSystem.getBaseSystemConfig();
        }

        try {
            JSONArray jsonArray = JSON.parseArray((String) val);
            JSONObject jsonObject = JSON.parseObject(objectMapper.writeValueAsString(jsonArray.get(1)));
            return JSON.toJavaObject(jsonObject, SystemBaseConfigVo.class);
        } catch (Exception exception) {
            throw new Blog4jException(ErrorEnum.PARSE_ERROR);
        }
    }


}
