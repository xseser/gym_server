package com.gym.user.registration.controller.request.valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidUserRegisterConfirmation {
    private String nickname;
    private Boolean verified;
}
