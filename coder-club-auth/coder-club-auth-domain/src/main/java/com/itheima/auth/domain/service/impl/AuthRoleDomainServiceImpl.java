package com.itheima.auth.domain.service.impl;

import com.itheima.auth.common.enums.IsDeleteFlagEnum;
import com.itheima.auth.domain.convert.AuthRoleBOConverter;
import com.itheima.auth.domain.entity.AuthRoleBO;
import com.itheima.auth.domain.service.AuthRoleDomainService;
import com.itheima.auth.infra.basic.entity.AuthRole;
import com.itheima.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;
    @Override
    public Boolean insert(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        authRole.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authRoleService.insert(authRole);
        return count > 0;

    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntity(authRoleBO);
        return authRoleService.update(authRole) > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        return authRoleService.update(authRole) > 0;
    }
}
