package com.gym.mail.management.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofSeconds(10))
                .setConnectTimeout(Duration.ofSeconds(30))
                .build();
    }
}
