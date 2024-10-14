package com.gym.bdd.tests.step.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@NoArgsConstructor
@Data
public class UserRegistrationResponse {

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

    @Nullable
    private Integer statusCode;

    public UserRegistrationResponse(
            @Nullable Integer statusCode,
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
        this.statusCode = statusCode;
    }

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
