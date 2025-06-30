package com.itheima.auth.application.convert;
import com.itheima.auth.application.dto.AuthUserDTO;
import com.itheima.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签DTO转换
 */
@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDTOToBO(AuthUserDTO authUserDTO);

    AuthUserDTO convertBOToDTO(AuthUserBO authUserBO);
}
