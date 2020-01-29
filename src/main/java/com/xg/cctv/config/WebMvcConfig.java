package com.xg.cctv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Lorenzo
 * @Date 2020/1/15 15:39
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }
}
