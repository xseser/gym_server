package com.gym.mail.management.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

import static com.response.gym.controller.url.UrlManagement.EMAIL_SECRET;

@Component
@Slf4j
public class MailControllerInterceptor implements HandlerInterceptor {

    private final String emailSecret;

    public MailControllerInterceptor(@Value("${gym.mail.secret}") String emailSecret) {
        this.emailSecret = emailSecret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String givenSecret = request.getHeader(EMAIL_SECRET);
        if (emailSecret.equals(givenSecret)) {
            return true;
        } else {
            response.sendError(403);
            return false;
        }
//        return true;
    }
}
