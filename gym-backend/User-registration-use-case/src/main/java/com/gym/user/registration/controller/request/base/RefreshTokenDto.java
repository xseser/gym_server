package com.gym.user.registration.controller.request.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDto {
    String refreshToken;

    @Override
    public String toString() {
        return "RefreshTokenDto{" +
               "refreshToken='" + "*****refreshToken***" + '\'' +
               '}';
    }
}
