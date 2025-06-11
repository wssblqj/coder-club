package com.itheima.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.itheima.auth.common.enums.AuthUserStatusEnum;
import com.itheima.auth.common.enums.IsDeleteFlagEnum;
import com.itheima.auth.domain.contants.AuthConstant;
import com.itheima.auth.domain.convert.AuthUserBOConverter;
import com.itheima.auth.domain.entity.AuthUserBO;
import com.itheima.auth.domain.service.AuthUserDomainService;
import com.itheima.auth.infra.basic.entity.AuthRole;
import com.itheima.auth.infra.basic.entity.AuthUser;
import com.itheima.auth.infra.basic.entity.AuthUserRole;
import com.itheima.auth.infra.basic.service.AuthRoleService;
import com.itheima.auth.infra.basic.service.AuthUserRoleService;
import com.itheima.auth.infra.basic.service.AuthUserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Component
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService  authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

    private final String salt = "12315";

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
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


}
