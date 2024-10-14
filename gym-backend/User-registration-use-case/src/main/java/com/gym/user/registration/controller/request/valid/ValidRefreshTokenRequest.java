package com.gym.user.registration.controller.request.valid;

import lombok.Data;

@Data
public class ValidRefreshTokenRequest {
    private String refreshToken;
}
