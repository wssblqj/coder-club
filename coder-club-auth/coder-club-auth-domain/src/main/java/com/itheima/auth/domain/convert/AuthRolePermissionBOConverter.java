package com.itheima.auth.domain.convert;

import com.itheima.auth.domain.entity.AuthPermissionBO;
import com.itheima.auth.domain.entity.AuthRolePermissionBO;
import com.itheima.auth.infra.basic.entity.AuthPermission;
import com.itheima.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 标签DTO转换
 */
@Mapper
public interface AuthRolePermissionBOConverter {

    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    AuthRolePermission convertBOToEntity(AuthRolePermissionBO authRolePermissionBO);
}
