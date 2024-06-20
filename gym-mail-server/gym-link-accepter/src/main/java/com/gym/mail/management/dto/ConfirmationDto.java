package com.gym.mail.management.dto;

import com.gym.mail.generator.model.EmailStatus;
import com.gym.mail.generator.model.EmailType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ConfirmationDto {

    private UUID id;
    private String nickname;
    private String mail;
    private String secret;
    private LocalDateTime expirationLinkTime;
    private EmailType linkType;
    private EmailStatus status;
}
