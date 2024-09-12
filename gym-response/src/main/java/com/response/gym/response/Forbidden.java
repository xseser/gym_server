package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;

public class Forbidden extends SpecifiedResponseDefinition {

    int code = HTTP_FORBIDDEN;

    public Forbidden() {
        this.statusCode = code;
    }

    public Forbidden(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Forbidden(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public Forbidden(Object body, RequestEntity.HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
