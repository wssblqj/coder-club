package com.itheima.auth.infra.basic.service.impl;

import com.itheima.auth.infra.basic.entity.AuthRolePermission;
import com.itheima.auth.infra.basic.mapper.AuthRolePermissionDao;
import com.itheima.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * (AuthRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2025-06-11 08:41:54
 */
@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRolePermission queryById(Long id) {
        return this.authRolePermissionDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.insert(authRolePermission);
    }

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public AuthRolePermission update(AuthRolePermission authRolePermission) {
        this.authRolePermissionDao.update(authRolePermission);
        return this.queryById(authRolePermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRolePermissionDao.deleteById(id) > 0;
    }


    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param List<AuthRolePermission> 实例对象列表
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<AuthRolePermission> authRolePermissionList) {
        return this.authRolePermissionDao.insertBatch(authRolePermissionList);
    }

    @Override
    public List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.queryAllByLimit(authRolePermission);
    }
}
