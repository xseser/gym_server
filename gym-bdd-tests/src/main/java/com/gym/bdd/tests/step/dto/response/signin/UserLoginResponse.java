package com.gym.bdd.tests.step.dto.response.signin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gym.bdd.tests.step.dto.response.ResponseMarker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserLoginResponse implements ResponseMarker {

    @JsonProperty("token")
    private String token;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("refreshToken")
    private String refreshToken;
}
