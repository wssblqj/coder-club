package com.itheima.auth.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AuthRole)实体类
 *
 * @author makejava
 * @since 2025-06-10 12:19:30
 */
@Data
public class AuthRole implements Serializable {
    private static final long serialVersionUID = -28750939758015256L;

    private Long id;

    private String roleName;

    private String roleKey;

    private String createdBy;

    private Date createdTime;

    private String updateBy;

    private Date updateTime;

    private Integer isDeleted;

}

