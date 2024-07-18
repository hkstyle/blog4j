package com.blog4j.article.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog4j.api.client.FeignUser;
import com.blog4j.api.vo.OrganizationVo;
import com.blog4j.api.vo.UserInfoVo;
import com.blog4j.article.context.CreateArticleContext;
import com.blog4j.article.context.UpdateArticleContext;
import com.blog4j.article.entity.ArticleEntity;
import com.blog4j.article.entity.CategoryEntity;
import com.blog4j.article.mapper.ArticleMapper;
import com.blog4j.article.mapper.CategoryMapper;
import com.blog4j.article.service.ArticleService;
import com.blog4j.article.vo.req.ArticleListReqVo;
import com.blog4j.article.vo.resp.ArticleRespVo;
import com.blog4j.article.vo.resp.ArticleStatusRespVo;
import com.blog4j.common.enums.ArticlePublicTypeEnum;
import com.blog4j.common.enums.ArticleStatusEnum;
import com.blog4j.common.enums.ErrorEnum;
import com.blog4j.common.enums.RoleEnum;
import com.blog4j.common.enums.YesOrNoEnum;
import com.blog4j.common.exception.Blog4jException;
import com.blog4j.common.utils.CommonUtil;
import com.blog4j.common.utils.IdGeneratorSnowflakeUtil;
import com.blog4j.api.vo.DeleteUserArticleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 98k灬
 * @version v1.0.0
 * @Description : 功能描述
 * @Create on : 2024/6/28 13:03
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private FeignUser feignUser;

    /**
     * 获取文章列表
     *
     * @param articleListReqVo 查询条件
     * @return 文章列表
     */
    @Override
    public PageInfo<ArticleRespVo> getArticleList(ArticleListReqVo articleListReqVo) {
        String categoryId = articleListReqVo.getCategoryId();
        String title = articleListReqVo.getTitle();
        Integer status = articleListReqVo.getStatus();

        this.checkCategory(categoryId);

        LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ArticleEntity::getCreateTime);
        if (StringUtils.isNotBlank(categoryId)) {
            wrapper.eq(ArticleEntity::getCategoryId, articleListReqVo.getCategoryId());
        }

        if (StringUtils.isNotBlank(title)) {
            wrapper.likeLeft(ArticleEntity::getTitle, articleListReqVo.getTitle());
        }

        if (Objects.nonNull(status)) {
            wrapper.eq(ArticleEntity::getStatus, status);
        }

        List<String> roleList = StpUtil.getRoleList();
        if (CollectionUtil.isEmpty(roleList)) {
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }
        String userId = StpUtil.getLoginIdAsString();
        String role = roleList.get(0);
        // 如果是超级管理员  可以查看所有的文章
        if (StringUtils.equals(role, RoleEnum.SUPER_ADMIN.getDesc())) {
            return this.getArticleList(wrapper, articleListReqVo);
        } else if (StringUtils.equals(role, RoleEnum.ORGANIZATION_ADMIN.getDesc())) {
            // 如果是组织管理员  可以查看该组织下的所有的文章
            List<String> userIds = feignUser.getUserIdsByOrganizationAdmin(userId);
            wrapper.in(ArticleEntity::getAuthorId, userIds).or()
                    .eq(ArticleEntity::getPublicType, ArticlePublicTypeEnum.VISIBLE_ALL.getCode());
            return this.getArticleList(wrapper, articleListReqVo);
        } else if (StringUtils.equals(role, RoleEnum.ORDINARY.getDesc())) {
            // 如果是普通用户
            List<OrganizationVo> organizationVoList = feignUser.getOrganizationInfoByUserId(userId);
            if (organizationVoList.isEmpty()) {
                // 如果该用户不是任何组织的成员
                wrapper.eq(ArticleEntity::getPublicType, ArticlePublicTypeEnum.VISIBLE_ALL.getCode());
                return this.getArticleList(wrapper, articleListReqVo);
            } else {
                HashSet<String> idSet = new HashSet<>();
                for (OrganizationVo vo : organizationVoList) {
                    String admin = vo.getOrganizationAdmin();
                    List<String> userIds = feignUser.getUserIdsByOrganizationAdmin(admin);
                    idSet.addAll(userIds);
                }
                PageHelper.startPage(articleListReqVo.getPageNo(), articleListReqVo.getPageSize());
                List<ArticleEntity> articleList = this.baseMapper.getOrdinaryArticleList(idSet, status, categoryId, title);
                if (!articleList.isEmpty()) {
                    List<ArticleRespVo> respVos = articleList.stream().map(article -> {
                        ArticleRespVo articleRespVo = new ArticleRespVo();
                        BeanUtils.copyProperties(article, articleRespVo);
                        return articleRespVo;
                    }).collect(Collectors.toList());
                    return new PageInfo<>(respVos);
                }
            }
        } else if (StringUtils.equals(role, RoleEnum.COMPOSER.getDesc())) {
            // 如果是创作者
            List<OrganizationVo> organizationVoList = feignUser.getOrganizationInfoByUserId(userId);
            List<ArticleEntity> articleList ;
            if (CollectionUtil.isEmpty(organizationVoList)) {
                // 如果该用户不是任何组织的成员
                PageHelper.startPage(articleListReqVo.getPageNo(), articleListReqVo.getPageSize());
                articleList = this.baseMapper.getComposerArticleList1(status, categoryId, title, userId);
            } else {
                HashSet<String> idSet = new HashSet<>();
                for (OrganizationVo vo : organizationVoList) {
                    String admin = vo.getOrganizationAdmin();
                    List<String> userIds = feignUser.getUserIdsByOrganizationAdmin(admin);
                    idSet.addAll(userIds);
                }
                PageHelper.startPage(articleListReqVo.getPageNo(), articleListReqVo.getPageSize());
                articleList = this.baseMapper.getComposerArticleList2(idSet, status, categoryId, title, userId);
            }
            if (!articleList.isEmpty()) {
                List<ArticleRespVo> respVos = articleList.stream().map(article -> {
                    ArticleRespVo articleRespVo = new ArticleRespVo();
                    BeanUtils.copyProperties(article, articleRespVo);
                    return articleRespVo;
                }).collect(Collectors.toList());
                return new PageInfo<>(respVos);
            }
        } else {
            // 该用户是访客
            wrapper.eq(ArticleEntity::getPublicType, ArticlePublicTypeEnum.VISIBLE_ALL.getCode());
            return this.getArticleList(wrapper, articleListReqVo);
        }
        return new PageInfo<>();
    }

    /**
     * 获取所有的文章状态
     *
     * @return 所有的文章状态
     */
    @Override
    public List<ArticleStatusRespVo> statusList() {
        List<ArticleStatusRespVo> list = new ArrayList<>();
        ArticleStatusRespVo val1 = ArticleStatusRespVo.builder()
                .code(1)
                .desc("草稿中")
                .build();
        ArticleStatusRespVo val2 = ArticleStatusRespVo.builder()
                .code(2)
                .desc("待发布")
                .build();
        ArticleStatusRespVo val3 = ArticleStatusRespVo.builder()
                .code(3)
                .desc("已发布")
                .build();
        list.add(val3);
        list.add(val2);
        list.add(val1);
        return list;
    }

    /**
     * 根据文章ID删除文章
     *
     * @param articleId 文章ID
     */
    @Override
    public void deleteArticle(String articleId) {
        this.beforeDeleteAndPublish(articleId);
        this.baseMapper.deleteById(articleId);
        // TODO 删除评论信息
    }

    /**
     * 根据文章ID发布文章
     *
     * @param articleId 文章ID
     */
    @Override
    public void publishArticle(String articleId) {
        ArticleEntity article = this.beforeDeleteAndPublish(articleId);
        article.setStatus(ArticleStatusEnum.ONLINE.getCode())
                .setPublishUserId(StpUtil.getLoginIdAsString())
                .setUpdateTime(CommonUtil.getCurrentDateTime());
        this.baseMapper.updateById(article);
    }

    /**
     * 查询文章详情信息
     *
     * @param articleId 文章ID
     * @return 文章详情信息
     */
    @Override
    public ArticleRespVo info(String articleId) {
        ArticleEntity article = this.baseMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        ArticleRespVo respVo = new ArticleRespVo();
        BeanUtils.copyProperties(article, respVo);
        return respVo;
    }

    /**
     * 编辑文章信息
     *
     * @param context 更新文章信息的上下文信息
     */
    @Override
    public void updateArticle(UpdateArticleContext context) {
        this.beforeUpdateArticle(context);
        ArticleEntity article = context.getArticle();
        BeanUtils.copyProperties(context, article);
        article.setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCategoryName(context.getCategory().getCategoryName());
        this.baseMapper.updateById(article);
    }

    /**
     * 创建文章信息
     *
     * @param context 创建文章信息的上下文信息
     */
    @Override
    public void create(CreateArticleContext context) {
        this.beforeCreate(context);
        ArticleEntity article = new ArticleEntity();
        BeanUtils.copyProperties(context, article);
        article.setArticleId(IdGeneratorSnowflakeUtil.snowflakeId())
                .setUpdateTime(CommonUtil.getCurrentDateTime())
                .setCreateTime(CommonUtil.getCurrentDateTime())
                .setDeleted(YesOrNoEnum.NO.getCode());
        this.baseMapper.insert(article);
    }

    /**
     * 删除用户名下的文章信息
     *
     * @param vo 用户集合
     */
    @Override
    public void deleteUserArticle(DeleteUserArticleVo vo) {
        List<String> userIds = vo.getUserIds();
        LambdaQueryWrapper<ArticleEntity> wrapper = new LambdaQueryWrapper<ArticleEntity>()
                .in(ArticleEntity::getAuthorId, userIds);
        List<ArticleEntity> articleList = this.baseMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(articleList)) {
            return;
        }
        Set<String> articleIds = articleList.stream()
                .map(ArticleEntity::getArticleId).collect(Collectors.toSet());
        this.baseMapper.deleteBatchIds(articleIds);

        // TODO 删除评论
    }

    // ------------------ private -------------------------------------------------------------

    private void checkCategory(String categoryId) {
        if (StringUtils.isBlank(categoryId)) {
            return;
        }
        CategoryEntity category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            log.error("category is not exist");
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
    }

    private PageInfo<ArticleRespVo> getArticleList(LambdaQueryWrapper<ArticleEntity> wrapper,
                                                   ArticleListReqVo articleListReqVo) {
        PageHelper.startPage(articleListReqVo.getPageNo(), articleListReqVo.getPageSize());
        List<ArticleEntity> articleList = this.baseMapper.selectList(wrapper);
        if (!articleList.isEmpty()) {
            List<ArticleRespVo> respVos = articleList.stream().map(article -> {
                ArticleRespVo articleRespVo = new ArticleRespVo();
                BeanUtils.copyProperties(article, articleRespVo);
                return articleRespVo;
            }).collect(Collectors.toList());
            return new PageInfo<>(respVos);
        }
        return new PageInfo<>();
    }

    private ArticleEntity beforeDeleteAndPublish(String articleId) {
        ArticleEntity article = this.baseMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }

        if (article.getStatus() != ArticleStatusEnum.WAIT.getCode()) {
            throw new Blog4jException(ErrorEnum.ARTICLE_STATUS_ILLEGAL);
        }

        List<String> roleList = StpUtil.getRoleList();
        if (roleList.isEmpty()) {
            log.error("roleList is empty .");
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        String role = roleList.get(0);
        String userId = StpUtil.getLoginIdAsString();
        String authorId = article.getAuthorId();

        // 如果角是创作者  只能删除或者发布自己发表的文章
        if (StringUtils.equals(role, RoleEnum.COMPOSER.getDesc()) &&
                !StringUtils.equals(userId, authorId)) {
            throw new Blog4jException(ErrorEnum.NO_PERMISSION_ERROR);
        }

        // 如果角色是组织管理员  只能删除或者发布自己组织下的用户发表的文章
        if (StringUtils.equals(role, RoleEnum.ORGANIZATION_ADMIN.getDesc())) {
            // 获取该组织管理员所有的用户ID列表
            List<String> userIds = feignUser.getUserIdsByOrganizationAdmin(userId);
            if (!userIds.contains(authorId)) {
                throw new Blog4jException(ErrorEnum.NO_PERMISSION_ERROR);
            }
        }
        return article;
    }

    private void beforeUpdateArticle(UpdateArticleContext context) {
        String articleId = context.getArticleId();
        ArticleEntity article = this.baseMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        context.setArticle(article);

        String categoryId = context.getCategoryId();
        CategoryEntity category = categoryMapper.selectById(categoryId);
        if (Objects.isNull(category)) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        if (category.getStatus() == YesOrNoEnum.YES.getCode()) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        context.setCategory(category);

        List<String> roleList = StpUtil.getRoleList();
        if (roleList.isEmpty()) {
            log.error("roleList is empty .");
            throw new Blog4jException(ErrorEnum.ROLE_INFO_EMPTY_ERROR);
        }

        String roleCode = roleList.get(0);
        String userId = StpUtil.getLoginIdAsString();

        // 如果角色是组织管理员或者创作者，只能编辑自己发表的文章
        if (StringUtils.equals(roleCode, RoleEnum.COMPOSER.getDesc()) ||
                StringUtils.equals(roleCode, RoleEnum.ORGANIZATION_ADMIN.getDesc())) {
            if (!StringUtils.equals(userId, article.getAuthorId())) {
                throw new Blog4jException(ErrorEnum.NO_PERMISSION_ERROR);
            }
        }
    }

    private void beforeCreate(CreateArticleContext context) {
        Integer timedRelease = context.getTimedRelease();
        if (timedRelease == YesOrNoEnum.YES.getCode()) {
            if (StringUtils.isBlank(context.getCronReleaseTime())) {
                throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
            }
            context.setStatus(ArticleStatusEnum.WAIT.getCode());
        }
        context.setStatus(ArticleStatusEnum.ONLINE.getCode());

        String userId = StpUtil.getLoginIdAsString();
        context.setUserId(userId);
        UserInfoVo userInfoVo = feignUser.getUserInfoByUserId(userId);
        context.setAuthorName(userInfoVo.getUserName())
                .setAuthorId(userId);

        CategoryEntity category = categoryMapper.selectById(context.getCategoryId());
        if (Objects.isNull(category)) {
            throw new Blog4jException(ErrorEnum.CATEGORY_INFO_EMPTY_ERROR);
        }

        if (category.getStatus() == YesOrNoEnum.YES.getCode()) {
            throw new Blog4jException(ErrorEnum.INVALID_PARAMETER_ERROR);
        }
        context.setCategoryName(category.getCategoryName());
    }
}
