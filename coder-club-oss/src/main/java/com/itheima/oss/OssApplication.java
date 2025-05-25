package com.itheima.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.itheima")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
