package com.gym.bdd.tests.step.dto.error;

public class InvalidErrorCodeException extends RuntimeException {

    public InvalidErrorCodeException(String message) {
        super(message);
    }
}
