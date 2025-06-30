package com.itheima.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.itheima.oss.entity.Result;
import com.itheima.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件上传Controller
 * @author qjl
 * @date 2025/6/29
 */
@RestController
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
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception{
        String url = fileService.getUrl(bucketName, objectName);
        return url;
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        return Result.ok(url);
    }

}
