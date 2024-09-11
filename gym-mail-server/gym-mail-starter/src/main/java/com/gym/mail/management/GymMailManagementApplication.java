package com.gym.mail.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.gym.mail.generator.repository")
@EntityScan(basePackages = "com.gym.mail.generator.model")
@SpringBootApplication(scanBasePackages = {
        "com.gym.mail.management",
        "com.gym.mail.generator",
        "com.gym.mail.adapter",
        "com.gym.mail.kafka" })
public class GymMailManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymMailManagementApplication.class, args);
    }
}
