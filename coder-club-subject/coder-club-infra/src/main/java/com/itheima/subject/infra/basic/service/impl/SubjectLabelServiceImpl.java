package com.itheima.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.subject.infra.basic.entity.SubjectLabel;
import com.itheima.subject.infra.basic.mapper.SubjectLabelDao;
import com.itheima.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2025-04-09 10:08:25
 */
@Service("subjectLabelService")
public class SubjectLabelServiceImpl implements SubjectLabelService {
    @Resource
    private SubjectLabelDao subjectLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLabel queryById(Long id) {
        return this.subjectLabelDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param subjectLabel 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<SubjectLabel> queryByPage(SubjectLabel subjectLabel) {
        long total = this.subjectLabelDao.count(subjectLabel);
        return null;
    }

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.insert(subjectLabel);
    }

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.update(subjectLabel);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectLabelDao.deleteById(id) > 0;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param labelIdList 主键
     * @return 实例对象
     */
    @Override
    public List<SubjectLabel> queryByIds(List<Long> labelIdList) {
        return this.subjectLabelDao.queryByIds(labelIdList);
    }


    @Override
    public List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.queryByCondition(subjectLabel);
    }
}
