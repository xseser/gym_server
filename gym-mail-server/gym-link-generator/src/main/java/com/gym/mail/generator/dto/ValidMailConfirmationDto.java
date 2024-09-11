package com.gym.mail.generator.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ValidMailConfirmationDto {
    private String mail;
    private String nickname;
    private String link;
    private LocalDateTime expirationDate;

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public ValidMailConfirmationDto(String mail, String nickname, String link, LocalDateTime expirationDate) {
        this.mail = mail;
        this.nickname = nickname;
        this.link = link;
        this.expirationDate = expirationDate;
    }
}
