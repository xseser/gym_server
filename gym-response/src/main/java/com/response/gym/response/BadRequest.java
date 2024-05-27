package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class BadRequest extends SpecifiedResponseDefinition {

    int code = HTTP_BAD_REQUEST;

    public BadRequest() {
        this.statusCode = code;
    }

    public BadRequest(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public BadRequest(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public BadRequest(Object body, RequestEntity.HeadersBuilder headersBuilder) {
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
