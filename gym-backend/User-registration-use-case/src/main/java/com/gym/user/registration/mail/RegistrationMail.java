package com.gym.user.registration.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationMail {
    private String nickname;
    private String mail;
    private Boolean isVerified;
}
