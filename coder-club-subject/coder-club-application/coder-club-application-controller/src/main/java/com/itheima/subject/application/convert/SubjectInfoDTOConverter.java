package com.itheima.subject.application.convert;

import com.itheima.subject.application.dto.SubjectInfoDTO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签DTO转换
 */
@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDtoToBo(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBoToDto(SubjectInfoBO resultBo);

    List<SubjectInfoDTO> convertBoListToDtoList(List<SubjectInfoBO> resultBoList);
}
