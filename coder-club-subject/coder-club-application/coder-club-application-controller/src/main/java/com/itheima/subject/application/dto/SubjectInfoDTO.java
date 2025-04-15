package com.itheima.subject.application.dto;

import com.itheima.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目DTO
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
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
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 分类id列表
     */
    private List<Integer> categoryIds;

    /**
     * 标签id列表
     */
    private List<Integer> labelIds;

    /**
     * 标签name
     */
    private List<String> labelName;

    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;

    /**
     *  分类id
     */
    private Long categoryId;

    /**
     * 标签id
     */
    private Long labelId;
}
