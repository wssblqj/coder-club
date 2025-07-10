package com.itheima.auth.api;

import com.itheima.auth.entity.AuthUserDTO;
import com.itheima.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 用户服务feign
 */

@FeignClient("coder-club-auth")
public interface UserFeignService {
    @RequestMapping("/user/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
