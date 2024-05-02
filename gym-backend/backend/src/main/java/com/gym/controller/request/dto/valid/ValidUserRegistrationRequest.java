package com.gym.controller.request.dto.valid;

import com.gym.model.user.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidUserRegistrationRequest {
    private String password;
    private String passwordMatcher;
    private String mail;
    private String nickname;
    private Gender gender;
}
