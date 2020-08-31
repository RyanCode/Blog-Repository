package com.ryan.blog.config.mvcConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Ryan
 * @Date 2020/7/22 12:31
 * version 1.0
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","HEAD","OPTIONS")
                .maxAge(3600);
    }
    @Bean
    public Interceptor getLoginInterceptor(){
        return new Interceptor();
    }

}
