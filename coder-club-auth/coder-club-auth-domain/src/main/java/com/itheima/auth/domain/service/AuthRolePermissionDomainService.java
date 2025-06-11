package com.itheima.auth.domain.service;

import com.itheima.auth.domain.entity.AuthRolePermissionBO;


public interface AuthRolePermissionDomainService {

    /**
     * 添加权限
     *
     * @param authPermissionBO
     * @return
     */
    Boolean add(AuthRolePermissionBO authPermissionBO);
}
