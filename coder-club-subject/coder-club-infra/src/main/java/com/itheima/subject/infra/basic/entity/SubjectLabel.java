package com.itheima.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2025-04-09 10:08:24
 */
@Data
public class SubjectLabel implements Serializable {
    private static final long serialVersionUID = 530943361333361653L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签名称
     */
    private String labelName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     */
    private Integer isDeleted;


}

