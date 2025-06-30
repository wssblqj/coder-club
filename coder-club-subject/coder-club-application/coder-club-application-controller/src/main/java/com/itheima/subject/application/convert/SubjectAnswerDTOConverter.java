package com.itheima.subject.application.convert;
import com.itheima.subject.application.dto.SubjectAnswerDTO;
import com.itheima.subject.application.dto.SubjectCategoryDTO;
import com.itheima.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签DTO转换
 */
@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDtoToBo(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertDtoToBo(List<SubjectAnswerDTO> subjectAnswerDTOList);

    SubjectCategoryDTO convertBoToDto(SubjectAnswerBO subjectAnswerBO);
}
