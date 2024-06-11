package com.gym.user.registration.controller.response;

import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;

public class UserLoginResponseDto {

    private Role role;
    private String nickname;
    private String token;

    public UserLoginResponseDto(User user, String token) {
        this.role = user.getRole();
        this.nickname = user.getNickname();
        this.token = token;
    }
}
