package com.ynhj.nativemysql.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @date: 2022/10/25
 * @author: yangniuhaojiang
 * @title: CORSFilter
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Configuration
@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }
}