package com.itheima.subject.domain.convert;


import com.itheima.subject.domain.entity.SubjectLikedBO;
import com.itheima.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLikedConverter {

    SubjectLikedConverter INSTANCE = Mappers.getMapper(SubjectLikedConverter.class);

    SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO);

    List<SubjectLikedBO> convertListInfoToBO(List<SubjectLiked> subjectLikedList);


}
