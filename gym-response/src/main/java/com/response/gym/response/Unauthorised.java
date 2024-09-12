package com.response.gym.response;

import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class Unauthorised extends SpecifiedResponseDefinition {

    int code = HTTP_UNAUTHORIZED;

    public Unauthorised() {
        this.statusCode = code;
    }

    public Unauthorised(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Unauthorised(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public Unauthorised(Object body, RequestEntity.HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
