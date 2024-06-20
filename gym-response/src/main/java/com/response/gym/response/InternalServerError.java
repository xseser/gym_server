package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class InternalServerError extends SpecifiedResponseDefinition {

    int code = HTTP_INTERNAL_ERROR;

    public InternalServerError() {
        this.statusCode = code;
    }

    public InternalServerError(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public InternalServerError(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public InternalServerError(Object body, RequestEntity.HeadersBuilder headersBuilder) {
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
