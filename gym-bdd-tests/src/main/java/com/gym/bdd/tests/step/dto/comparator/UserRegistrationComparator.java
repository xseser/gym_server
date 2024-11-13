package com.gym.bdd.tests.step.dto.comparator;

import com.gym.bdd.tests.step.dto.response.UserRegistrationResponse;
import lombok.Data;

import java.util.Objects;

@Data
public class UserRegistrationComparator {
    private String mail;
    private String nickname;
    private String gender;
    private String role;

    public UserRegistrationComparator(String mail, String nickname, String gender, String role) {
        this.gender = Objects.requireNonNullElse(gender, "UNKNOWN");
        this.mail = mail;
        this.nickname = nickname;
        this.role = role;
    }

    public static UserRegistrationComparator map(UserRegistrationResponse userRegistrationResponse) {
        return new UserRegistrationComparator(
                userRegistrationResponse.getMail(),
                userRegistrationResponse.getNickname(),
                userRegistrationResponse.getGender(),
                userRegistrationResponse.getRole());
    }
}
