package com.itheima.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.subject.common.entity.PageResult;
import com.itheima.subject.infra.basic.entity.SubjectInfoEs;
import com.itheima.subject.infra.basic.service.SubjectEsService;
import com.itheima.subject.infra.entity.UserInfo;
import com.itheima.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/feign")
public class TestFeignController {

    @Resource
    private UserRpc userRpc;

    @Resource
    private SubjectEsService subjectEsService;

    @RequestMapping("/testFeign")
    public void testFeign() {
        UserInfo admin = userRpc.getUserInfo("admin");
        System.out.println(admin);
    }


}
