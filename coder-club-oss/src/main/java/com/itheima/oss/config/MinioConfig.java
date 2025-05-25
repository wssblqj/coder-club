package com.itheima.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    /**
     * 存储桶名称
     */
    @Value("${minio.url}")
    private String url;

    /**
     * 访问密钥
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * 密钥
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 获取MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

}
