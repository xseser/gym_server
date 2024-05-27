package com.response.gym.response;

import org.springframework.http.RequestEntity.HeadersBuilder;

import static java.net.HttpURLConnection.HTTP_OK;

public class Ok extends SpecifiedResponseDefinition {

    int code = HTTP_OK;

    public Ok() {
        this.statusCode = code;
    }

    public Ok(HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Ok(Object body) {
        this.responseBody = body;
        this.statusCode = code;
    }

    public Ok(Object body, HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
