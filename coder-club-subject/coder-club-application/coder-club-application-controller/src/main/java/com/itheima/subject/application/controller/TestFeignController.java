package com.itheima.subject.application.controller;

import com.itheima.subject.infra.entity.UserInfo;
import com.itheima.subject.infra.rpc.UserRpc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
public class TestFeignController {

    @Resource
    private UserRpc userRpc;


    @RequestMapping("/testFeign")
    public void testFeign() {
        UserInfo admin = userRpc.getUserInfo("admin");
        System.out.println(admin);
    }
}
