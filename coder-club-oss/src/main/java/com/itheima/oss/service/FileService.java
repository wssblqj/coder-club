package com.itheima.oss.service;


import com.itheima.oss.adapter.StorageAdapter;
import com.itheima.oss.entity.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

    @Resource
    private StorageAdapter storageAdapter;

    /**
     * 创建bucket
     */
    public void createBucket(String bucketName) {
        storageAdapter.createBucket(bucketName);
    }

    /**
     * 上传文件
     */
    public void uploadFile(MultipartFile file, String bucketName, String objectName) {
        storageAdapter.uploadFile(file, bucketName, objectName);
    }

    /**
     * 获取所有bucket
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    /**
     * 获取所有文件
     */
    public List<FileInfo> getAllFile(String bucket) {
        return storageAdapter.getAllFile(bucket);
    }

    /**
     * 获取文件
     */
    public InputStream download(String bucketName, String objectName) {
        return storageAdapter.download(bucketName, objectName);
    }

    /**
     * 删除文件
     */
    public void deleteFile(String bucketName, String objectName) {
        storageAdapter.deleteFile(bucketName, objectName);
    }


    /**
     * 删除bucket
     */
    public void deleteBucket(String bucketName) {
        storageAdapter.deleteBucket(bucketName);
    }
}
