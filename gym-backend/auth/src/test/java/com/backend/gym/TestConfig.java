package com.backend.gym;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.backend.gym", "gym.mmt.auth.config", "com.mmt.gym.initialiser"})
public class TestConfig {
}
