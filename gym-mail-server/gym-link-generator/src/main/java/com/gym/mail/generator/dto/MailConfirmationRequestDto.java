package com.gym.mail.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MailConfirmationRequestDto {
    private String nickname;
    private String mail;
}
