package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectCategoryBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import com.itheima.subject.infra.basic.entity.SubjectCategory;
import com.itheima.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);
    List<SubjectLabelBO> convertLabelToBo(List<SubjectLabel> subjectLabelList);
}
