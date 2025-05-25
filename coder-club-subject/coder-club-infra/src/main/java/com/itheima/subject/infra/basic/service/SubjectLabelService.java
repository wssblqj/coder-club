package com.itheima.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.subject.infra.basic.entity.SubjectLabel;

import java.util.List;


/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2025-04-09 10:08:25
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    public Page<SubjectLabel> queryByPage(SubjectLabel subjectLabel);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据labelIds查询label
     */
    List<SubjectLabel> queryByIds(List<Long> labelIdList);

    /**
     * 根据条件查询label
     */
    List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel);
}
