package com.gym.bdd.tests.step.dto.error;

public record ErrorDto(Integer errorCode) {

    public ErrorDto {
        if (errorCode == null) {
            throw new InvalidErrorCodeException("nullable code exception");
        }
    }
}
