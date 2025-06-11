package com.itheima.auth.domain.service.impl;

import com.itheima.auth.common.enums.IsDeleteFlagEnum;
import com.itheima.auth.domain.convert.AuthPermissionBOConverter;
import com.itheima.auth.domain.entity.AuthPermissionBO;
import com.itheima.auth.domain.service.AuthPermissionDomainService;
import com.itheima.auth.infra.basic.entity.AuthPermission;
import com.itheima.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean insert(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        authPermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authPermissionService.insert(authPermission);
        return count > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntity(authPermissionBO);
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        Integer count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
