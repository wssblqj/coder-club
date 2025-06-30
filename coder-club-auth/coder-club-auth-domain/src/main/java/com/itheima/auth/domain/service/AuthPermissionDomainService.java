package com.itheima.auth.domain.service;

import com.itheima.auth.domain.entity.AuthPermissionBO;

import java.util.List;

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

    /**
     * 查询权限
     *
     * @param userName
     * @return
     */
    List<String> getPermission(String userName);
}
