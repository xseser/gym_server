package com.gym.common.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public abstract class MMTResponseCreator extends Body {

    int statusCode;
    Object body;
    HttpHeaders headers;

    public ResponseEntity makeResponse() {
        return new ResponseEntity(responseBody, headers, statusCode);
    }
}
