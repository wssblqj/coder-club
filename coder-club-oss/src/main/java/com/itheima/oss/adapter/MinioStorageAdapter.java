package com.itheima.oss.adapter;

import com.itheima.oss.entity.FileInfo;
import com.itheima.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service("minioStorageAdapter")
public class MinioStorageAdapter implements StorageAdapter {


    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            minioUtil.uploadFile(file.getInputStream(), bucketName, objectName + "/" + file.getOriginalFilename());
        } else {
            minioUtil.uploadFile(file.getInputStream(), bucketName, file.getOriginalFilename());
        }
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucket) {
        return minioUtil.getAllFile(bucket);
    }

    @Override
    @SneakyThrows
    public InputStream download(String bucketName, String objectName) {
        return minioUtil.download(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucketName, String objectName) {
        minioUtil.deleteFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucketName) {
        minioUtil.deleteBucket(bucketName);
    }
}
