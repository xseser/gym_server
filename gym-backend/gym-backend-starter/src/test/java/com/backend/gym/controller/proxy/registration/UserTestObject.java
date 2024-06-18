package com.backend.gym.controller.proxy.registration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class UserTestObject {
    private String mail;
    private String nickname;
    private String gender;
    private String role;
}
