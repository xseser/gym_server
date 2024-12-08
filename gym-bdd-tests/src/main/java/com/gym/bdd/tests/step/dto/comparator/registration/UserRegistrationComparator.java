package com.gym.bdd.tests.step.dto.comparator.registration;

import com.gym.bdd.tests.step.dto.comparator.Comparator;
import com.gym.bdd.tests.step.dto.request.registration.UserRegistrationRequest;
import com.gym.bdd.tests.step.dto.response.registration.UserRegistrationResponse;

import java.util.Objects;
import java.util.Optional;

public record UserRegistrationComparator(String id, String mail, String nickname, String gender, String role) implements Comparator {

    public UserRegistrationComparator(UserRegistrationRequest request, String role) {
        this(
                null,
                request.getMail(),
                request.getNickName(),
                Optional.ofNullable(request.getGender()).orElse("UNKNOWN"),
                role);
    }

    public UserRegistrationComparator(UserRegistrationResponse response) {
        this(
                response.getId(),
                response.getMail(),
                response.getNickname(),
                response.getGender(),
                response.getRole());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserRegistrationComparator that = (UserRegistrationComparator) o;
        return Objects.equals(mail, that.mail) &&
               Objects.equals(role, that.role) &&
               Objects.equals(gender, that.gender) &&
               Objects.equals(nickname, that.nickname);
    }

    @Override
    public void validate() {
        if (id == null) {
            throw new NullableIdException("Invalid id after registration");
        }
    }
}
