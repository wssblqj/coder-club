package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.infra.basic.entity.SubjectBrief;
import com.itheima.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConverter {

    BriefSubjectConverter INSTANCE = Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);
}
