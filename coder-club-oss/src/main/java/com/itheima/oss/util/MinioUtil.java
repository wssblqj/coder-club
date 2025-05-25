package com.itheima.oss.util;


import com.itheima.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建bucket
     */
    public void createBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 上传文件
     */
    public void uploadFile(InputStream inputStream, String bucketName, String objectName) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                .object(objectName).stream(inputStream, -1, Integer.MAX_VALUE).build());
    }

    /**
     * 列出所有bucket
     */
    public List<String> getAllBucket() throws Exception{
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     *
     */
    public List<FileInfo> getAllFile(String bucket) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucket).build());
        List<FileInfo> fileInfos = new ArrayList<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(item.objectName());
            fileInfo.setDir(item.isDir());
            fileInfo.setEtag(item.etag());
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }

    public InputStream download(String bucketName, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String bucketName, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
