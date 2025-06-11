package com.itheima.auth.application.convert;
import com.itheima.auth.application.dto.AuthPermissionDTO;
import com.itheima.auth.application.dto.AuthRoleDTO;
import com.itheima.auth.domain.entity.AuthPermissionBO;
import com.itheima.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 标签DTO转换
 */
@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDTOToBO(AuthPermissionDTO authPermissionDTO);
}
