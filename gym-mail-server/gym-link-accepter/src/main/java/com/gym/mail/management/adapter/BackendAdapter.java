package com.gym.mail.management.adapter;

import com.gym.mail.management.rest.RestTemplateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BackendAdapter<T> {

    private final RestTemplateWrapper restTemplate;

    public BackendAdapter(RestTemplateWrapper restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity sendMailReceiveConfirmation(T t) {
        ResponseEntity response = restTemplate.post(
                "http://gym-backend:9876/MMT/api/v1/confirm",
                getHeaders(),
                t,
                String.class);
        log.info("response: {}", response.getBody());
        return response;
    }

    private HttpHeaders getHeaders() {
        return new HttpHeaders();
    }
}
