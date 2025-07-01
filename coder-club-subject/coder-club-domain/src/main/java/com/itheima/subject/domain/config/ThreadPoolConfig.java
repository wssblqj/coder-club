package com.itheima.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {


    @Bean("labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                20,
                100,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(40),
                new CustomNameFactoryFactory("label"),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        return threadPoolExecutor;
    }
}
