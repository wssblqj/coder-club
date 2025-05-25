package com.itheima.oss.adapter;

import com.itheima.oss.entity.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service("aliStorageAdapter")
public class AliStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(MultipartFile file, String bucketName, String objectName) {

    }

    @Override
    public List<String> getAllBucket() {
        return Collections.emptyList();
    }

    @Override
    public List<FileInfo> getAllFile(String bucket) {
        return Collections.emptyList();
    }

    @Override
    public InputStream download(String bucketName, String objectName) {
        return null;
    }

    @Override
    public void deleteFile(String bucketName, String objectName) {

    }

    @Override
    public void deleteBucket(String bucketName) {

    }
}
