package com.itheima.subject.infra.rpc;

import com.itheima.auth.api.UserFeignService;
import com.itheima.auth.entity.AuthUserDTO;
import com.itheima.auth.entity.Result;
import com.itheima.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRpc {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if(result.getSuccess()) {
            AuthUserDTO data = result.getData();
            userInfo.setUserName(data.getUserName());
            userInfo.setNickName(data.getNickName());
            userInfo.setAvatar(data.getAvatar());
            return userInfo;
        }
        return userInfo;
    }
}
