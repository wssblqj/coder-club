package com.itheima.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.itheima.auth.common.enums.AuthUserStatusEnum;
import com.itheima.auth.common.enums.IsDeleteFlagEnum;
import com.itheima.auth.domain.contants.AuthConstant;
import com.itheima.auth.domain.convert.AuthUserBOConverter;
import com.itheima.auth.domain.entity.AuthUserBO;
import com.itheima.auth.domain.redis.RedisUtil;
import com.itheima.auth.domain.service.AuthUserDomainService;
import com.itheima.auth.infra.basic.entity.*;
import com.itheima.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService  authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthPermissionService authPermissionService;

    private final String salt = "12315";

    private String authPermisssionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    private static final String Login_PREFIX = "loginCode";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        //检验用户是否存在
        List<AuthUser> existList = authUserService.queryByCondition(existAuthUser);
        if(existList.size() > 0) {
            return false;
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        if(!StringUtils.isBlank(authUser.getPassword())) {
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole queryRole = authRoleService.queryByCondition(authRole);
        Long roleId = queryRole.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService
                .queryByCondition(authRolePermission);
        List<Long> permissionList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> authPermissionList = authPermissionService.queryByList(permissionList);
        String permissionKey = redisUtil.buildKey(authPermisssionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(authPermissionList));
        return count > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validateCode) {
        String loginKey = redisUtil.buildKey(Login_PREFIX, validateCode);
        String openId = redisUtil.get(loginKey);
        if(StringUtils.isBlank(openId)) {
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register(authUserBO);
        StpUtil.login(openId);
        return StpUtil.getTokenInfo();
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setUserName(authUserBO.getUserName());
        List<AuthUser> authUsers = authUserService.queryByCondition(authUser);
        if(CollectionUtils.isEmpty(authUsers)) {
            return new AuthUserBO();
        }
        AuthUser user = authUsers.get(0);
        return AuthUserBOConverter.INSTANCE.convertEntityToBO(user);
    }


}
