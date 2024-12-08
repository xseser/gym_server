package com.gym.bdd.tests.step.dto.request.signin;

import com.gym.bdd.tests.step.dto.request.RequestMarker;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginInRequest implements RequestMarker {
    private String nickname;
    private String password;
}
