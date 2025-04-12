package com.itheima.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签DTO
 */
@Data
public class SubjectLabelDTO implements Serializable {
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
}
