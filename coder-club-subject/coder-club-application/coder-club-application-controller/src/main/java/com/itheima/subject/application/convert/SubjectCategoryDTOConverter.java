package com.itheima.subject.application.convert;

import com.itheima.subject.application.dto.SubjectCategoryDTO;
import com.itheima.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO  convertDtoToBo(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoListToDtoList(List<SubjectCategoryBO> subjectCategoryBOList);

    SubjectCategoryDTO convertBoToDto(SubjectCategoryBO subjectCategoryBO);

}
