package com.response.gym.response;

import com.response.gym.response.types.ErrorResponse;
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

    public Unauthorised(int errorCode) {
        this.responseBody = new ErrorResponse(errorCode);
        this.statusCode = code;
    }

    public Unauthorised(Object body, RequestEntity.HeadersBuilder headersBuilder){
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
