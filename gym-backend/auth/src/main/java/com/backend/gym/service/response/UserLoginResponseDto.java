package com.backend.gym.service.response;

import com.backend.gym.model.user.Role;
import com.backend.gym.model.user.User;

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
