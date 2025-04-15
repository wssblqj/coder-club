package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.infra.basic.entity.SubjectMultiple;
import com.itheima.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MultipleSubjectConverter {

    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    SubjectAnswerBO convertEntityToAnswerBo(SubjectMultiple subjectMultiple);
}
