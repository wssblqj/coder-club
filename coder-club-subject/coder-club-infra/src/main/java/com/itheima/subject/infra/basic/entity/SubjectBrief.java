package com.itheima.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 简答题信息表(SubjectBrief)实体类
 *
 * @author makejava
 * @since 2025-04-10 12:16:13
 */
@Data
public class SubjectBrief implements Serializable {
    private static final long serialVersionUID = 399467954969675729L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 题目id
     */
    private Long subjectId;
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

