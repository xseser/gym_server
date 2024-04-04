package com.gym.controller.processor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidUserRegistrationRequest {
    private String password;
    private String passwordMatcher;
    private String mail;
    private String nickname;
}
