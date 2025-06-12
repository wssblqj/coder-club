package com.itheima.auth.domain.convert;

import com.itheima.auth.domain.entity.AuthRoleBO;
import com.itheima.auth.infra.basic.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T19:28:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class AuthRoleBOConverterImpl implements AuthRoleBOConverter {

    @Override
    public AuthRole convertBOToEntity(AuthRoleBO authRoleBO) {
        if ( authRoleBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBO.getId() );
        authRole.setRoleName( authRoleBO.getRoleName() );
        authRole.setRoleKey( authRoleBO.getRoleKey() );

        return authRole;
    }
}
