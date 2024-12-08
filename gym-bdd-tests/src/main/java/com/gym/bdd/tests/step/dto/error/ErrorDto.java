package com.gym.bdd.tests.step.dto.error;

public record ErrorDto(Integer code) {

    public ErrorDto {
        if (code == null) {
            throw new InvalidErrorCodeException("nullable code exception");
        }
    }
}
