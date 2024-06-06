package com.gym.user.registration.controller.request.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    private String nickName;
    private String mail;
    private String password;
    private String passwordMatcher;
    private String gender;

    @Override
    public String toString() {
        return "UserRegistrationResponseDto{" +
               "nickName='" + nickName + '\'' +
               ", mail='" + mail + '\'' +
               ", gender='" + gender + '\'' +
               ", password='" + "**********" + '\'' +
               ", passwordMatcher='" + "**********" + '\'' +
               '}';
    }
}
