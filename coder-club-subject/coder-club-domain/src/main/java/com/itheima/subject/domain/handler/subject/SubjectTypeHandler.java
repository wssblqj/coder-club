package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.entity.SubjectInfoBO;

public interface SubjectTypeHandler {
    /**
     * 枚举身份的识别
     * @return
     */
    SubjectInfoTypeEnum getType();

    /**
     * 实际题目插入
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);
}
