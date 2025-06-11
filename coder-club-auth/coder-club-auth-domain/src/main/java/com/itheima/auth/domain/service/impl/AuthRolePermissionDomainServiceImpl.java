package com.itheima.auth.domain.service.impl;

import com.itheima.auth.common.enums.IsDeleteFlagEnum;
import com.itheima.auth.domain.entity.AuthRolePermissionBO;
import com.itheima.auth.domain.service.AuthRolePermissionDomainService;
import com.itheima.auth.infra.basic.entity.AuthRolePermission;
import com.itheima.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        Long roleId = authRolePermissionBO.getRoleId();
        List<AuthRolePermission> authRolePermissionList = new LinkedList<>();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            authRolePermissionList.add(authRolePermission);
        });
        int count = authRolePermissionService.insertBatch(authRolePermissionList);
        return count > 0;
    }
}
