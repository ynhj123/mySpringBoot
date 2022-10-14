package com.test.config;

import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebConfig
 * @description:
 * @author: wwb
 * @date: 2018-04-09 09:49:59
 * @version: ver 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer, ErrorPageRegistrar {



    /** 添加自定义拦截器
     * @Description:
     * @author:
     * @Date: 2017-12-08 10:30:47
     * @param: registry
     * @return: void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
    }



}
