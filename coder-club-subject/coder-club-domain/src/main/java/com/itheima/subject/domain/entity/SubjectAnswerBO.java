package com.itheima.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案DTO
 */
@Data
public class SubjectAnswerBO implements Serializable {
    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}
