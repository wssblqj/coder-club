package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.MultipleSubjectConverter;
import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.infra.basic.entity.SubjectMultiple;
import com.itheima.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 但选题目的策略类
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getType() {

        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectMultiple> subjectMultipleList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiple.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(int subjectId) {
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        List<SubjectAnswerBO> subjectAnswerBOList = new ArrayList<>();
        List<SubjectMultiple> subjectMultipleList = subjectMultipleService.queryBySubjectId(Long.valueOf(subjectId));
        subjectMultipleList.forEach(subjectMultiple -> {
            SubjectAnswerBO answerBO = MultipleSubjectConverter.INSTANCE.convertEntityToAnswerBo(subjectMultiple);
            subjectAnswerBOList.add(answerBO);
        });
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
