package com.gym.mail.management.controller.dto;

import com.gym.mail.generator.model.MailConfirmation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MockedMailConfirmation {
    private UUID id;
    private String nickname;
    private String mail;
    private String secret;
    private LocalDateTime expirationLinkTime;
    private String linkType;
    private String emailStatus;

    public MockedMailConfirmation(MailConfirmation mailConfirmation) {
        this.id = mailConfirmation.getId();
        this.nickname = mailConfirmation.getNickname();
        this.mail = mailConfirmation.getMail();
        this.secret = mailConfirmation.getSecret();
        this.expirationLinkTime = mailConfirmation.getExpirationLinkTime();
        this.linkType = mailConfirmation.getLinkType().name();
        this.emailStatus = mailConfirmation.getEmailStatus().name();
    }
}
