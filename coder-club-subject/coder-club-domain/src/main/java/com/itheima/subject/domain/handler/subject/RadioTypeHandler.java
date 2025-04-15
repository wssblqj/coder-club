package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.MultipleSubjectConverter;
import com.itheima.subject.domain.convert.RadioSubjectConverter;
import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.infra.basic.entity.SubjectMultiple;
import com.itheima.subject.infra.basic.entity.SubjectRadio;
import com.itheima.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 但选题目的策略类
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectRadio> subjectRadioList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(int subjectId) {
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        List<SubjectAnswerBO> subjectAnswerBOList = new ArrayList<>();
        List<SubjectRadio> subjectRadioList = subjectRadioService.queryBySubjectId(Long.valueOf(subjectId));
        subjectRadioList.forEach(subjectRadio -> {
            SubjectAnswerBO answerBO = RadioSubjectConverter.INSTANCE.convertEntityToAnswerBo(subjectRadio);
            subjectAnswerBOList.add(answerBO);
        });
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
