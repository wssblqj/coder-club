package com.itheima.subject.application.convert;

import com.itheima.subject.application.dto.SubjectCategoryDTO;
import com.itheima.subject.application.dto.SubjectLabelDTO;
import com.itheima.subject.domain.entity.SubjectCategoryBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签DTO转换
 */
@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToBo(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBoToDto(List<SubjectLabelBO> subjectLabelBOList);

    List<SubjectLabelDTO> convertBoListToDtoList(List<SubjectLabelBO> boList);
}
