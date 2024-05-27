package com.backend.gym.controller.request.dto.valid;

import com.backend.gym.model.user.Gender;
import com.backend.gym.service.response.UserRegistrationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ValidUserRegistrationRequest {
    private String password;
    private String passwordMatcher;
    private String mail;
    private String nickname;

    public ValidUserRegistrationRequest(String password, String passwordMatcher, String mail, String nickname, Gender gender) {
        this.password = password;
        this.passwordMatcher = passwordMatcher;
        this.mail = mail;
        this.nickname = nickname;
        this.gender = gender;
    }

    private Gender gender;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordMatcher(String passwordMatcher) {
        this.passwordMatcher = passwordMatcher;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordMatcher() {
        return passwordMatcher;
    }

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ValidUserRegistrationRequest() {

    }

    @Override
    public String toString() {
        return "ValidUserRegistrationRequest{" +
               "password='" + "*******" + '\'' +
               ", passwordMatcher='" + "*******" + '\'' +
               ", mail='" + mail + '\'' +
               ", nickname='" + nickname + '\'' +
               ", gender=" + gender +
               '}';
    }
}
