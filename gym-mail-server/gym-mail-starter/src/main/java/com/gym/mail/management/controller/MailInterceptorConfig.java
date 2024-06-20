package com.gym.mail.management.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.response.gym.controller.url.UrlManagement.EMAIL_SEND;

@Configuration
public class MailInterceptorConfig implements WebMvcConfigurer {

    private final MailControllerInterceptor mailControllerInterceptor;

    public MailInterceptorConfig(MailControllerInterceptor mailControllerInterceptor) {
        this.mailControllerInterceptor = mailControllerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mailControllerInterceptor)
                .addPathPatterns("**/" + EMAIL_SEND);
    }
}
