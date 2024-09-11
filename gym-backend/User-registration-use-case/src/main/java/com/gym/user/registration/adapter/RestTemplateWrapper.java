package com.gym.user.registration.adapter;

import com.response.gym.response.InternalServerError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestTemplateWrapper {

    private final RestTemplate restTemplate;

    public RestTemplateWrapper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> delete(String url, HttpHeaders headers, Class<T> responseClass) {
        return this.doRequest(url, HttpMethod.DELETE, headers, (Object)null, responseClass);
    }

    public <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> responseClass) {
        return this.doRequest(url, HttpMethod.GET, headers, (Object)null, responseClass);
    }

    public <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object body, Class<T> responseClass) {
        return this.doRequest(url, HttpMethod.POST, headers, body, responseClass);
    }

    public <T> ResponseEntity<T> put(String url, HttpHeaders headers, Object body, Class<T> responseClass) {
        return this.doRequest(url, HttpMethod.PUT, headers, body, responseClass);
    }

    private <T> ResponseEntity doRequest(String url, HttpMethod method, HttpHeaders headers, Object body, Class<T> responseClass) {
        HttpEntity httpEntity = new HttpEntity(body, headers);

        try {
            log.info("Sending request url: {}, method: {}, body: {}", new Object[]{url, method, body});
            return this.restTemplate.exchange(url, method, httpEntity, responseClass, new Object[0]);
        } catch (RestClientResponseException var8) {
            log.error("Couldn't connect with url: {}, statusCode: {}, response: {}", new Object[]{url, var8.getRawStatusCode(), var8.getResponseBodyAsString()});
            return new InternalServerError().makeResponse();
        }
    }
}