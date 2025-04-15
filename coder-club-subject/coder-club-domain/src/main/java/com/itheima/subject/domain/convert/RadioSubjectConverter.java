package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.infra.basic.entity.SubjectInfo;
import com.itheima.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE = Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    SubjectAnswerBO convertEntityToAnswerBo(SubjectRadio subjectRadio);
}
