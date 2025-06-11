package com.itheima.auth.infra.basic.entity;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * (AuthUserRole)实体类
 *
 * @author makejava
 * @since 2025-06-10 16:21:15
 */
@Data
public class AuthUserRole implements Serializable {
    private static final long serialVersionUID = 715140312517627876L;

    private Long id;

    private Long userId;

    private Long roleId;

    private String createdBy;

    private Date createdTime;

    private String updateBy;

    private Date updateTime;

    private Integer isDeleted;

}

