package com.itheima.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AuthRolePermission)实体类
 *
 * @author makejava
 * @since 2025-06-11 08:41:54
 */
@Data
public class AuthRolePermission implements Serializable {
    private static final long serialVersionUID = -38447929129590246L;

    private Long id;

    private Long roleId;

    private Long permissionId;

    private String createdBy;

    private Date createdTime;

    private String updateBy;

    private Date updateTime;

    private Integer isDeleted;

}

