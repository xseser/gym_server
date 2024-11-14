package com.gym.bdd.tests.step.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserLoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("refreshToken")
    private String refreshToken;
}
