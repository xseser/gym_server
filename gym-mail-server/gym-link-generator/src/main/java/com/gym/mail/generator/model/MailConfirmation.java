package com.gym.mail.generator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mail_confirmation", schema = "gym")
public class MailConfirmation {

    @Id
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "mail")
    private String mail;

    @Column(name = "secret")
    private String secret;

    @Column(name = "link_expiration_date")
    private LocalDateTime expirationLinkTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_type")
    private EmailType linkType;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_status")
    private EmailStatus emailStatus;

    public boolean isLinkExpired() {
        return LocalDateTime.now().isBefore(expirationLinkTime);
    }
}
