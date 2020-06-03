package com.hh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {
    @Value("${user.file.path}")
    private String imgFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//映射图片保存地址
        registry.addResourceHandler("admin/img/**").addResourceLocations("file:"+imgFilePath);
    }
}
