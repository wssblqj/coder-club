package com.itheima.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2025-04-10 12:14:14
 */
@Data
public class SubjectInfo implements Serializable {
    private static final long serialVersionUID = -34002189920640494L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人姓名
     */
    private String settleName;
    /**
     * 题目类型 1 单选 2 多选 3 判断 4 简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
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
    private String updateTime;

    /**
     * 删除标识
     */
    private Integer isDeleted;


    private Integer subjectCount;

}

