package com.gym.controller.request.dto.base;

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

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
               "nickName='" + nickName + '\'' +
               ", mail='" + mail + '\'' +
               ", password='" + "**********" + '\'' +
               ", passwordMatcher='" + "**********" + '\'' +
               '}';
    }
}
