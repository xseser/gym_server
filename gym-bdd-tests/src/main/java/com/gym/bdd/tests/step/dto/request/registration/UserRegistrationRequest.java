package com.gym.bdd.tests.step.dto.request.registration;

import com.gym.bdd.tests.step.dto.request.RequestMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegistrationRequest implements RequestMarker {
    private String mail;
    private String password;
    private String passwordMatcher;
    private String nickName;
    private String gender;
}
