package com.gym.mail.adapter.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Slf4j
@Configuration
public class GymMailSender {

    public final String host;
    private final Integer port;
    private final String username;
    private final String password;

    public GymMailSender(
            @Value("${spring.mail.host}") String host,
            @Value("${spring.mail.port}") Integer port,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        return javaMailSender;
    }
}
