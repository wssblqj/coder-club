package com.itheima.subject.infra.basic.service;

import com.itheima.subject.infra.basic.entity.SubjectMapping;

import java.util.List;


/**
 * 题目分类关系表(SubjectMapping)表服务接口
 *
 * @author makejava
 * @since 2025-04-09 12:34:05
 */
public interface SubjectMappingService {

    /**
     * 通过ID查询单条数据
     */
    SubjectMapping queryById(int id);

    /**
     * 分页查询
     *
     * @param subjectMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    SubjectMapping insert(SubjectMapping subjectMapping);

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    int update(SubjectMapping subjectMapping);

    /**
     * 通过主键删除数据
     */
    boolean deleteById(int id);

    /**
     * 查询分类下标签id
     */
    List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping);

    /**
     * 批量插入
     */

    void batchInsert(List<SubjectMapping> subjectMappings);
}
