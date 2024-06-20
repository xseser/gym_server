package com.gym.user.registration.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.response.gym.controller.url.UrlManagement.EMAIL_SECRET;
import static com.response.gym.controller.url.UrlManagement.EMAIL_TYPE;

@Slf4j
@Component
public class GymMailAdapter {

    private final RestTemplateWrapper restTemplate;
    private final String mailSecret;

    public GymMailAdapter(
            RestTemplateWrapper restTemplate,
            @Value("${gym.mail.secret}") String mailSecret) {
        this.restTemplate = restTemplate;
        this.mailSecret = mailSecret;
    }

    public void sendRegistrationMail(String nickname, String mail) {
        ResponseEntity response = restTemplate.post(
                "http://gym-mail:5443/MMT/api/v1/trigger/email",
                getHeaders(),
                getBody(nickname, mail),
                String.class);
        log.info("response: {}", response.getBody());
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(EMAIL_SECRET, mailSecret);
        httpHeaders.add(EMAIL_TYPE,"REGISTRATION");
        return httpHeaders;
    }

    private MailConfirmationDto getBody(String nickname, String mail) {
        return MailConfirmationDto.builder()
                .nickname(nickname)
                .mail(mail)
                .build();
    }
}
