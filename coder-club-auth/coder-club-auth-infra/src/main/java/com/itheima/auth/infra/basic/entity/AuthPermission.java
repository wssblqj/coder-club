package com.itheima.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AuthPermission)实体类
 *
 * @author makejava
 * @since 2025-06-10 18:25:48
 */
@Data
public class AuthPermission implements Serializable {
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

    private String createdBy;

    private Date createdTime;

    private String updateBy;

    private Date updateTime;

    private Integer isDeleted;
}

