package com.fconlineserverv1.global.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .defaultHeader("x-nxopen-api-key", "test_14b03a8733ac1e22631d797917842297da0e7983841cb91f64e3bae57f811974efe8d04e6d233bd35cf2fabdeb93fb0d")
                .build();
//        return new RestTemplate();
    };
}
