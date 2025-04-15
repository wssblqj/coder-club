package com.itheima.subject.infra.basic.mapper;

import com.itheima.subject.infra.basic.entity.SubjectMultiple;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表数据库访问层
 *
 * @author makejava
 * @since 2025-04-10 12:18:35
 */
public interface SubjectMultipleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param subjectMultiple 查询条件
     * @return 对象列表
     */
    List<SubjectMultiple> queryAllByLimit(SubjectMultiple subjectMultiple);

    /**
     * 统计总行数
     *
     * @param subjectMultiple 查询条件
     * @return 总行数
     */
    long count(SubjectMultiple subjectMultiple);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 影响行数
     */
    int insert(SubjectMultiple subjectMultiple);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectMultiple> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectMultiple> entities);


    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 影响行数
     */
    int update(SubjectMultiple subjectMultiple);


    /**
     * 根据题目id查询选项
     *
     * @param subjectId
     * @return
     */
    List<SubjectMultiple> queryBySubjectId(Long subjectId);
}

