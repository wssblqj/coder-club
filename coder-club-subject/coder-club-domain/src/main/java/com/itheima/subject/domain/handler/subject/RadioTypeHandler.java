package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.RadioSubjectConverter;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.infra.basic.entity.SubjectInfo;
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
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }
}
