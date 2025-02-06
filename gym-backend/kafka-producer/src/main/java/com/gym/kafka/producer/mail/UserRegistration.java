package com.gym.kafka.producer.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserRegistration extends EventDto {
    private String verification;

    public UserRegistration(String nickname, String mail, boolean verification) {
        super(nickname, mail);
        this.verification = String.valueOf(verification);
    }
}
