package com.blog4j.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.user.entity.PermissionEntity;
import com.blog4j.user.entity.RoleEntity;
import com.blog4j.user.entity.RolePermissionRelEntity;
import com.blog4j.user.mapper.PermissionMapper;
import com.blog4j.user.mapper.RoleMapper;
import com.blog4j.user.mapper.RolePermissionRelMapper;
import com.blog4j.user.service.PermissionService;
import com.blog4j.user.vo.req.CreateNodeReqVo;
import com.blog4j.user.vo.req.DeletePermissionNodeReqVo;
import com.blog4j.user.vo.req.EditNodeReqVo;
import com.blog4j.user.vo.req.SaveRolePermissionRelReqVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/7/7 00:32
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity>
        implements PermissionService {
    @Autowired
    private RolePermissionRelMapper rolePermissionRelMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取树形结构的权限列表
     *
     * @return 权限列表
     */
    @Override
    public List<PermissionEntity> getTreePermissions() {
        List<PermissionEntity> permissionEntities = this.baseMapper.selectList(null);
        List<PermissionEntity> list = new ArrayList<>();
        for (PermissionEntity entity : permissionEntities) {
            if (entity.getParentId() == 0) {
                list.add(entity);
            }
        }
        for (PermissionEntity entity : list) {
            childrenMenu(permissionEntities, entity);
        }
        return list;
    }

    /**
     * 根据角色ID查询权限信息列表
     *
     * @param roleId 角色ID
     * @return 权限信息列表
     */
    @Override
    public List<Integer> getPermissionByRoleId(String roleId) {
        LambdaQueryWrapper<RolePermissionRelEntity> wrapper = new LambdaQueryWrapper<RolePermissionRelEntity>()
                .eq(RolePermissionRelEntity::getRoleId, roleId);
        List<RolePermissionRelEntity> rolePermissionRelList = rolePermissionRelMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(rolePermissionRelList)) {
            return null;
        }

        List<Integer> permissionIds = rolePermissionRelList.stream().map(RolePermissionRelEntity::getPermissionId).collect(Collectors.toList());
        List<PermissionEntity> permissionList = this.baseMapper.selectBatchIds(permissionIds);
        List<PermissionEntity> list = permissionList.stream().filter(item -> !Objects.equals(item.getParentId(), 0)).collect(Collectors.toList());
        return list.stream().map(PermissionEntity::getPermissionId).collect(Collectors.toList());
    }

    /**
     * 保存角色与权限的关系
     *
     * @param reqVo 信息
     */
    @Override
    public void saveRolePermissionRel(SaveRolePermissionRelReqVo reqVo) {
        String roleId = reqVo.getRoleId();
        List<Integer> permissionIds = reqVo.getPermissionIds();

        RoleEntity role = roleMapper.selectById(roleId);
        if (Objects.isNull(role)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        // 将原来该角色对应的权限全部删除
        LambdaQueryWrapper<RolePermissionRelEntity> wrapper = new LambdaQueryWrapper<RolePermissionRelEntity>()
                .eq(RolePermissionRelEntity::getRoleId, roleId);
        List<RolePermissionRelEntity> rolePermissionRelList = rolePermissionRelMapper.selectList(wrapper);
        if (CollectionUtil.isNotEmpty(rolePermissionRelList)) {
            Set<Integer> ids = rolePermissionRelList.stream()
                    .map(RolePermissionRelEntity::getId).collect(Collectors.toSet());
            this.baseMapper.deleteBatchIds(ids);
        }

        if (CollectionUtil.isEmpty(permissionIds)) {
            return;
        }

        rolePermissionRelMapper.batchSave(roleId, permissionIds);
    }

    /**
     * 删除节点
     *
     * @param reqVo 信息
     */
    @Override
    public void deletePermissionNode(DeletePermissionNodeReqVo reqVo) {
        Integer permissionId = reqVo.getPermissionId();
        Integer parentId = reqVo.getParentId();
        if (parentId == 0) {
            // 删除父节点
            LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<PermissionEntity>()
                    .eq(PermissionEntity::getPermissionId, permissionId)
                    .eq(PermissionEntity::getParentId, parentId);
            this.baseMapper.delete(wrapper);
            LambdaQueryWrapper<PermissionEntity> wrapper1 = new LambdaQueryWrapper<PermissionEntity>()
                    .eq(PermissionEntity::getParentId, permissionId);
            this.baseMapper.delete(wrapper1);
        } else {
            // 删除子节点
            LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<PermissionEntity>()
                    .eq(PermissionEntity::getPermissionId, permissionId)
                    .eq(PermissionEntity::getParentId, parentId);
            this.baseMapper.delete(wrapper);

            // 判断该子节点的父节点下面是否还有子节点  如果没有了  将该父节点也删除
            LambdaQueryWrapper<PermissionEntity> wrapper1 = new LambdaQueryWrapper<PermissionEntity>()
                    .eq(PermissionEntity::getParentId, parentId);
            Integer count = this.baseMapper.selectCount(wrapper1);
            if (count == 0) {
                LambdaQueryWrapper<PermissionEntity> wrapper2 = new LambdaQueryWrapper<PermissionEntity>()
                        .eq(PermissionEntity::getPermissionId, parentId);
                this.baseMapper.delete(wrapper2);
            }
        }
    }

    /**
     * 新增权限
     *
     * @param reqVo 信息
     */
    @Override
    public void createNode(CreateNodeReqVo reqVo) {
        this.beforeCreateNode(reqVo);
        PermissionEntity build = PermissionEntity.builder()
                .updateTime(CommonUtil.getCurrentDateTime())
                .createTime(CommonUtil.getCurrentDateTime())
                .parentId(reqVo.getParentId())
                .permissionName(reqVo.getPermissionName())
                .permissionCode(reqVo.getPermissionCode())
                .build();
        this.baseMapper.insert(build);
    }

    /**
     * 编辑权限
     *
     * @param reqVo 信息
     */
    @Override
    public void editNode(EditNodeReqVo reqVo) {
        this.beforeEdit(reqVo);
        PermissionEntity permission = new PermissionEntity();
        BeanUtils.copyProperties(reqVo, permission);
        permission.setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(permission);
    }

    private void beforeEdit(EditNodeReqVo reqVo) {
        Integer permissionId = reqVo.getPermissionId();
        PermissionEntity permission = this.baseMapper.selectById(permissionId);
        if (Objects.isNull(permission)) {
            throw new Blog4jException(ErrorEnum.PERMISSION_INFO_EMPTY_ERROR);
        }

        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<PermissionEntity>()
                .ne(PermissionEntity::getPermissionId, permissionId)
                .eq(PermissionEntity::getPermissionName, reqVo.getPermissionName());
        if (this.baseMapper.selectCount(wrapper) > 0) {
            throw new Blog4jException(ErrorEnum.PERMISSION_NAME_REPEAT_ERROR);
        }

        LambdaQueryWrapper<PermissionEntity> wrapper1 = new LambdaQueryWrapper<PermissionEntity>()
                .ne(PermissionEntity::getPermissionId, permissionId)
                .eq(PermissionEntity::getPermissionCode, reqVo.getPermissionCode());
        if (this.baseMapper.selectCount(wrapper1) > 0) {
            throw new Blog4jException(ErrorEnum.PERMISSION_CODE_REPEAT_ERROR);
        }
    }

    private void beforeCreateNode(CreateNodeReqVo reqVo) {
        Integer parentId = reqVo.getParentId();
        String permissionName = reqVo.getPermissionName();
        String permissionCode = reqVo.getPermissionCode();
        LambdaQueryWrapper<PermissionEntity> wrapper = new LambdaQueryWrapper<PermissionEntity>()
                .eq(PermissionEntity::getPermissionId, parentId);
        PermissionEntity permission = this.baseMapper.selectOne(wrapper);
        if (Objects.isNull(permission)) {
            throw new Blog4jException(ErrorEnum.PERMISSION_INFO_EMPTY_ERROR);
        }

        // 权限代码不能重复
        LambdaQueryWrapper<PermissionEntity> wrapper1 = new LambdaQueryWrapper<PermissionEntity>()
                .eq(PermissionEntity::getPermissionCode, permissionCode);
        if (this.baseMapper.selectCount(wrapper1) > 0) {
            throw new Blog4jException(ErrorEnum.PERMISSION_CODE_REPEAT_ERROR);
        }

        // 权限名称不能重复
        LambdaQueryWrapper<PermissionEntity> wrapper2 = new LambdaQueryWrapper<PermissionEntity>()
                .eq(PermissionEntity::getPermissionName, permissionName);
        if (this.baseMapper.selectCount(wrapper2) > 0) {
            throw new Blog4jException(ErrorEnum.PERMISSION_NAME_REPEAT_ERROR);
        }
    }

    private PermissionEntity childrenMenu(List<PermissionEntity> menuList, PermissionEntity menu) {
        List<PermissionEntity> children = new ArrayList<>();
        for (PermissionEntity m : menuList) {
            if (Objects.equals(m.getParentId(), menu.getPermissionId())) {
                children.add(childrenMenu(menuList, m));
            }
        }
        menu.setChildren(children);
        return menu;
    }
}
