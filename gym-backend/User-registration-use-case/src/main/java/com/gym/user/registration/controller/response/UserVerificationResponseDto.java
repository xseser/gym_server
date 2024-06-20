package com.gym.user.registration.controller.response;

import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import lombok.Data;

@Data
public class UserVerificationResponseDto extends AuthResponse {
    private String id;
    private String nickname;
    private Role role;
    private boolean isVerified;

    public UserVerificationResponseDto(User user) {
        this.id = user.getId().toString();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.isVerified = user.getIsVerified();
    }
}
