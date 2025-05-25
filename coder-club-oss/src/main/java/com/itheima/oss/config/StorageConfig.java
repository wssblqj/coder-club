package com.itheima.oss.config;

import com.itheima.oss.adapter.AliStorageAdapter;
import com.itheima.oss.adapter.MinioStorageAdapter;
import com.itheima.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@EnableAutoConfiguration
public class StorageConfig {

    @Value("${storage.type}")
    private String storageType;

    @Bean
    @RefreshScope
    public StorageAdapter storageAdapter() {
        if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else if ("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        } else {
            throw new IllegalArgumentException("storageType is not valid");
        }
    }
}
