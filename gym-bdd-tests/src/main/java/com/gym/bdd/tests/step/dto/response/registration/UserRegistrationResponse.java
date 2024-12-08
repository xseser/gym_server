package com.gym.bdd.tests.step.dto.response.registration;

import com.gym.bdd.tests.step.dto.response.ResponseMarker;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@NoArgsConstructor
@Data
public class UserRegistrationResponse implements ResponseMarker {

    @Nullable
    private String id;

    @Nullable
    private String mail;

    @Nullable
    private String nickname;

    @Nullable
    private String gender;

    @Nullable
    private String role;

    public UserRegistrationResponse(
            @Nullable String id,
            @Nullable String mail,
            @Nullable String nickname,
            @Nullable String gender,
            @Nullable String role) {
        this.id = id;
        this.mail = mail;
        this.nickname = nickname;
        this.gender = gender;
        this.role = role;
    }
}
