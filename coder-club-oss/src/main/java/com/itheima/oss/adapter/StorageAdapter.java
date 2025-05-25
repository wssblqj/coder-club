package com.itheima.oss.adapter;

import com.itheima.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface StorageAdapter {

    /**
     * 创建bucket
     */
    void createBucket(String bucketName);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile file, String bucketName, String objectName);

    /**
     * 获取所有bucket
     */
    List<String> getAllBucket();

    /**
     * 获取所有文件
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 获取文件
     */
    InputStream download(String bucketName, String objectName);

    /**
     * 删除文件
     */
    void deleteFile(String bucketName, String objectName);


    /**
     * 删除bucket
     */
    void deleteBucket(String bucketName);

}
