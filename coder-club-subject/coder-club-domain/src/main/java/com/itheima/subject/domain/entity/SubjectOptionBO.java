package com.itheima.subject.domain.entity;

import com.itheima.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目标签BO
 */
@Data
public class SubjectOptionBO extends PageInfo implements Serializable {
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;
}
