package com.itheima.subject.domain.convert;

import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertInfoListToBoInfo(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertOptionToBo(SubjectOptionBO subjectOptionBO, SubjectInfo subjectInfo);
}
