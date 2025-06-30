package com.itheima.oss.adapter;

import com.itheima.oss.entity.FileInfo;
import com.itheima.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Service("minioStorageAdapter")
public class MinioStorageAdapter implements StorageAdapter {


    @Resource
    private MinioUtil minioUtil;

    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;


    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        // 将文件读入内存， 构造可复用流
        byte[] bytes = file.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        String finalObjectName = (objectName != null) ? objectName + "/" + file.getOriginalFilename() : file.getOriginalFilename();
        minioUtil.uploadFile(bais, bucketName, finalObjectName, bytes.length, file.getContentType());
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

    @Override
    public String getUrl(String bucket, String objectName) {
        return url + "/" + bucket + "/" + objectName;
    }
}
