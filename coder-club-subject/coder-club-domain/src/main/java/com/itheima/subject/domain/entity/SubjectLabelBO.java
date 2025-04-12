package com.itheima.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签BO
 */
@Data
public class SubjectLabelBO implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类id
     */
    private Long categoryId;
}
