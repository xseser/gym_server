package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_CONFLICT;

public class Conflict extends SpecifiedResponseDefinition {

    int code = HTTP_CONFLICT;

    public Conflict() {
        this.statusCode = code;
    }

    public Conflict(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Conflict(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public Conflict(Object body, RequestEntity.HeadersBuilder headersBuilder) {
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
