package com.itheima.subject.infra.basic.service;

import com.itheima.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2025-04-10 12:18:35
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);


    /**
     * 批量插入
     * @param subjectMultipleList
     */
    void batchInsert(List<SubjectMultiple> subjectMultipleList);

    /**
     * 根据题目id查询选项
     * @param aLong
     * @return
     */
    List<SubjectMultiple> queryBySubjectId(Long aLong);
}
