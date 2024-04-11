package com.lhg.apiserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://192.168.0.201:3000",
                        "http://13.209.62.108", "http://lhg1006.store", "http://www.lhg1006.store",
                        "https://13.209.62.108", "https://lhg1006.store", "https://www.lhg1006.store"
                        );
    }
}
