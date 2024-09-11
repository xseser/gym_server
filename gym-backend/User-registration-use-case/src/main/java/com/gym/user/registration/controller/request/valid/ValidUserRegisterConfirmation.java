package com.gym.user.registration.controller.request.valid;

import lombok.Data;

@Data
public class ValidUserRegisterConfirmation {
    private String nickname;
    private Boolean verified;
}
