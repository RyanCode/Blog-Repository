package com.ryan.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Author Ryan
 * @Date 2020/7/20 15:12
 * version 1.0
 */
@SpringBootApplication
@CrossOrigin
@EnableCaching
@MapperScan(value = "com.ryan.blog.mapper")
public class BlogApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
