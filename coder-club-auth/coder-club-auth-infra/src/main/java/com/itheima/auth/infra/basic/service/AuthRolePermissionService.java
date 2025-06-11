package com.itheima.auth.infra.basic.service;

import com.itheima.auth.infra.basic.entity.AuthRolePermission;

import java.util.List;

/**
 * (AuthRolePermission)表服务接口
 *
 * @author makejava
 * @since 2025-06-11 08:41:54
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);


    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    Integer insert(AuthRolePermission authRolePermission);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 批量插入
     *
     * @param authRolePermissionList 实体列表
     * @return 影响行数
     */
    int insertBatch(List<AuthRolePermission> authRolePermissionList);
}
