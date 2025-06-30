package com.itheima.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.itheima")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class, args);
    }
}
