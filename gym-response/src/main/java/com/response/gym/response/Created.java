package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_CREATED;

public class Created extends SpecifiedResponseDefinition {

    int code = HTTP_CREATED;

    public Created() {
        this.statusCode = code;
    }

    public Created(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Created(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public Created(Object body, RequestEntity.HeadersBuilder headersBuilder) {
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
