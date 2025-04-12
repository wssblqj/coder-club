package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.infra.basic.entity.SubjectJudge;
import com.itheima.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JudgeSubjectConverter {

    JudgeSubjectConverter INSTANCE = Mappers.getMapper(JudgeSubjectConverter.class);

    SubjectJudge convertBoToEntity(SubjectAnswerBO subjectAnswerBO);
}
