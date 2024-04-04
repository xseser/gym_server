package com.gym.controller;

import com.gym.common.response.BadRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public abstract class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity handleExceptionDuringValidation(IllegalArgumentException code) {
        return new BadRequest(code.getMessage()).makeResponse(); //TODO check
    }
}
