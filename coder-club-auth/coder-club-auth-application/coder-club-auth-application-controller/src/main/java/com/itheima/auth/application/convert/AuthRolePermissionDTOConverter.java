package com.itheima.auth.application.convert;
import com.itheima.auth.application.dto.AuthPermissionDTO;
import com.itheima.auth.application.dto.AuthRolePermissionDTO;
import com.itheima.auth.domain.entity.AuthPermissionBO;
import com.itheima.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 标签DTO转换
 */
@Mapper
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
