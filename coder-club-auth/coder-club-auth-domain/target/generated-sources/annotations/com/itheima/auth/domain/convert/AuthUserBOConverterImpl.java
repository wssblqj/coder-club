package com.itheima.auth.domain.convert;

import com.itheima.auth.domain.entity.AuthUserBO;
import com.itheima.auth.infra.basic.entity.AuthUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T09:57:57+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class AuthUserBOConverterImpl implements AuthUserBOConverter {

    @Override
    public AuthUser convertBOToEntity(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId( authUserBO.getId() );
        authUser.setUserName( authUserBO.getUserName() );
        authUser.setNickName( authUserBO.getNickName() );
        authUser.setEmail( authUserBO.getEmail() );
        authUser.setPhone( authUserBO.getPhone() );
        authUser.setPassword( authUserBO.getPassword() );
        authUser.setSex( authUserBO.getSex() );
        authUser.setAvatar( authUserBO.getAvatar() );
        authUser.setStatus( authUserBO.getStatus() );
        authUser.setIntroduce( authUserBO.getIntroduce() );
        authUser.setExtJson( authUserBO.getExtJson() );
        authUser.setCreatedBy( authUserBO.getCreatedBy() );
        authUser.setCreatedTime( authUserBO.getCreatedTime() );
        authUser.setUpdateBy( authUserBO.getUpdateBy() );
        authUser.setUpdateTime( authUserBO.getUpdateTime() );
        authUser.setIsDeleted( authUserBO.getIsDeleted() );

        return authUser;
    }

    @Override
    public AuthUserBO convertEntityToBO(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUser.getId() );
        authUserBO.setUserName( authUser.getUserName() );
        authUserBO.setNickName( authUser.getNickName() );
        authUserBO.setEmail( authUser.getEmail() );
        authUserBO.setPhone( authUser.getPhone() );
        authUserBO.setPassword( authUser.getPassword() );
        authUserBO.setSex( authUser.getSex() );
        authUserBO.setAvatar( authUser.getAvatar() );
        authUserBO.setStatus( authUser.getStatus() );
        authUserBO.setIntroduce( authUser.getIntroduce() );
        authUserBO.setExtJson( authUser.getExtJson() );
        authUserBO.setCreatedBy( authUser.getCreatedBy() );
        authUserBO.setCreatedTime( authUser.getCreatedTime() );
        authUserBO.setUpdateBy( authUser.getUpdateBy() );
        authUserBO.setUpdateTime( authUser.getUpdateTime() );
        authUserBO.setIsDeleted( authUser.getIsDeleted() );

        return authUserBO;
    }
}
