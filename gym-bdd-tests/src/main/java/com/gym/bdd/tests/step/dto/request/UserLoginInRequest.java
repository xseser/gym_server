package com.gym.bdd.tests.step.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginInRequest {
    private String nickname;
    private String password;
}
