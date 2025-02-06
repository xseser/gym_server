package com.backend.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {
        "gym.mmt.auth",
        "com.core.gym",
        "com.backend.gym",
        "com.gym.user.registration",
        "com.gym.kafka.producer"
})
@EnableJpaRepositories(basePackages = {
        "com.gym.user.registration",
        "com.gym.kafka.producer.repository"
})
@EntityScan(basePackages = {
        "com.gym.user.registration",
        "com.gym.kafka.producer.model"
})
@EnableTransactionManagement
public class GymBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymBackendApplication.class, args);
    }
}
