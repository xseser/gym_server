package com.backend.gym.controller.proxy.registration;

import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;
import com.response.gym.response.types.ErrorResponse;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

class TestUserRegistrationDataProvider implements DataProvider {

    private final String password;
    private final String mail;
    private String gender;
    private final String passwordMatcher;
    private final String nickname;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public TestUserRegistrationDataProvider() {
        String pass = getPassword();
        this.password = pass;
        this.passwordMatcher = pass;
        this.nickname = getNickName();
        this.gender = getGender();
        this.mail = getMail();
    }

    protected UserRegistrationDto provideValidUserRegistrationDto() {
        return UserRegistrationDto.builder()
                .mail(mail)
                .gender(gender)
                .nickName(nickname)
                .password(password)
                .passwordMatcher(passwordMatcher)
                .build();
    }

    protected void assertValidRegistrationResponse(ResponseEntity responseEntity) {
        //given
        UserRegistrationResponseDto response = ((UserRegistrationResponseDto) responseEntity.getBody());

        //then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        System.out.println(response.getGender().toString());
        System.out.println(gender);
        Assertions.assertThat(response.getGender().toString()).isEqualTo(Objects.requireNonNullElse(gender, Gender.UNKNOWN.toString()));
        Assertions.assertThat(response.getMail()).isEqualTo(mail);
        Assertions.assertThat(response.getNickname()).isEqualTo(nickname);
        Assertions.assertThat(response.getRole()).isEqualTo(Role.MEMBER);
    }

    protected void assertInvalidRegistrationResponse(ResponseEntity responseEntity, HttpStatus status, int errorCode) {
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(status);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(new ErrorResponse(errorCode));
    }
}
