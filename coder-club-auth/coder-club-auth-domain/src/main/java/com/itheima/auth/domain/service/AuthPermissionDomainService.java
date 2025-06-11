package com.itheima.auth.domain.service;

import com.itheima.auth.domain.entity.AuthPermissionBO;

public interface AuthPermissionDomainService {

    /**
     * 添加权限
     *
     * @param authPermissionBO
     * @return
     */
    Boolean insert(AuthPermissionBO authPermissionBO);

    /**
     * 修改权限
     *
     * @param authPermissionBO
     * @return
     */
    Boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     *
     * @param authPermissionBO
     * @return
     */
    Boolean delete(AuthPermissionBO authPermissionBO);
}
