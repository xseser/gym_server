package com.response.gym.response;

import com.response.gym.response.types.ErrorResponse;
import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class NotFound extends SpecifiedResponseDefinition {

    int code = HTTP_NOT_FOUND;

    public NotFound() {
        this.statusCode = code;
    }

    public NotFound(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public NotFound(int errorCode) {
        this.responseBody = new ErrorResponse(errorCode);
        this.statusCode = code;
    }

    public NotFound(Object body, RequestEntity.HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
