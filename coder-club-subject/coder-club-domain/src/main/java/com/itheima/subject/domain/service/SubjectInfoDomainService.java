package com.itheima.subject.domain.service;

import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectInfoDomainService {

    /**
     * 新增标签
     */
    void add(SubjectInfoBO subjectInfoBO);

//    /**
//     * 更新标签
//     */
//    Boolean update(SubjectLabelBO subjectLabelBO);
//
//    /**
//     * 删除标签
//     */
//    Boolean delete(SubjectLabelBO subjectLabelBO);
//
//    /**
//     * 根据类型查询标签
//     */
//    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
