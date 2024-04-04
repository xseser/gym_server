package com.gym.common.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDto {
    private String nickName;
    private String mail;
    private String password;
    private String passwordConfirm;
}
