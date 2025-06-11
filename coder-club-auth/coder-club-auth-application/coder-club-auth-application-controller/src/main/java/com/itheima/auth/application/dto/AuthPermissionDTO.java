package com.itheima.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * (AuthUser)实体类
 *
 * @author makejava
 * @since 2025-06-09 10:52:53
 */
@Data
public class AuthPermissionDTO implements Serializable {
    private static final long serialVersionUID = 127014835422377092L;

    private Long id;

    private String name;

    private Long parentId;

    private Integer type;

    private String menuUrl;

    private Integer status;

    private Integer show;

    private String icon;

    private String permissionKey;

}

