package com.response.gym.response;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public abstract class SpecifiedResponseDefinition extends MMTResponseCreator {

    HttpHeaders header = new HttpHeaders();

    public void makeCorrectHeadersValue(String headerName, String headerValue){
        header.add(headerName, headerValue);
        this.headers = header;
    }

    public void makeCorrectHeadersValue(Map<String, String> headers){
        header.setAll(headers);
        this.headers = header;
    }
}
