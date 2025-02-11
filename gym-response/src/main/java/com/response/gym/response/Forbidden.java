package com.response.gym.response;

import com.response.gym.response.types.ErrorResponse;
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

    public Forbidden(int errorCode) {
        this.responseBody = new ErrorResponse(errorCode);
        this.statusCode = code;
    }

    public Forbidden(Object body, RequestEntity.HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
