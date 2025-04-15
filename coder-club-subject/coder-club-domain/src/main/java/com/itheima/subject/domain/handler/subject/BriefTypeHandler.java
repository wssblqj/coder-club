package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.BriefSubjectConverter;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.infra.basic.entity.SubjectBrief;
import com.itheima.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 但选题目的策略类
 */

@Component
public class BriefTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getType() {
        return SubjectInfoTypeEnum.BRIEF;
    }


    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBrief.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(int subjectId) {
        SubjectBrief subjectBrief = subjectBriefService.queryBySubjectId(Long.valueOf(subjectId));
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBO;
    }
}
