package com.itheima.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthUser)实体类
 *
 * @author makejava
 * @since 2025-06-09 10:52:53
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -28750939758015256L;

    private Long id;

    private String roleName;

    private String roleKey;

}

