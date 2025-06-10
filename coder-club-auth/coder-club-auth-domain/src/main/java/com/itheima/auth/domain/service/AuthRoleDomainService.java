package com.itheima.auth.domain.service;

import com.itheima.auth.domain.entity.AuthRoleBO;
import com.itheima.auth.domain.entity.AuthUserBO;

public interface AuthRoleDomainService {


    /**
     * 添加角色
     *
     * @param authRoleBO
     * @return
     */
    Boolean insert(AuthRoleBO authRoleBO);

    /**
     * 修改角色
     *
     * @param authRoleBO
     * @return
     */
    Boolean update(AuthRoleBO authRoleBO);


    /**
     * 删除角色
     *
     * @param authRoleBO
     * @return
     */
    Boolean delete(AuthRoleBO authRoleBO);
}
