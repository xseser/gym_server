package com.response.gym.response;

import com.response.gym.response.types.CommonResponse;
import org.springframework.http.RequestEntity;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;

public class Accepted extends SpecifiedResponseDefinition {

    int code = HTTP_ACCEPTED;

    public Accepted() {
        this.statusCode = code;
    }

    public Accepted(RequestEntity.HeadersBuilder headersBuilder) {
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }

    public Accepted(int responseCode) {
        this.responseBody = new CommonResponse(responseCode);
        this.statusCode = code;
    }

    public Accepted(Object body, RequestEntity.HeadersBuilder headersBuilder) {
        this.responseBody = body;
        this.headers = headersBuilder.build().getHeaders();
        this.statusCode = code;
    }
}
