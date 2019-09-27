package com.youyu.cotenant.config;

import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoEasyConfig {

    @Value("${goeasy.rest-host}")
    private String restHost;

    @Value("${goeasy.app-key}")
    private String appKey;

    @Bean
    public GoEasy goEasy() {
        GoEasy goEasy = new GoEasy(restHost, appKey);
        return goEasy;
    }
}
