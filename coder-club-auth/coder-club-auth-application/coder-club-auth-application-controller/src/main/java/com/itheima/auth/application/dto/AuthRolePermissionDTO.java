package com.itheima.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2025-06-11 08:41:54
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -38447929129590246L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private List<Long> permissionIdList;

}

