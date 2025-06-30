package com.itheima.auth.domain.convert;
import com.itheima.auth.domain.entity.AuthUserBO;
import com.itheima.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 标签DTO转换
 */
@Mapper
public interface AuthUserBOConverter {

    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);

    AuthUserBO convertEntityToBO(AuthUser authUser);
}
