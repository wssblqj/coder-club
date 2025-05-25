package com.itheima.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.itheima.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.type}", autoRefreshed = true)
    private String storageType;

    @RequestMapping("/testGetAllBucket")
    public String testGetAllBucket() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() throws Exception {
        return storageType;
    }
 }
