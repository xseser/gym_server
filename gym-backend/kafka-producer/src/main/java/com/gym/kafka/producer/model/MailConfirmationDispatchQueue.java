package com.gym.kafka.producer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "email_confirmation_dispatch_queue")
public class MailConfirmationDispatchQueue {

    @Id
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "send_attempts")
    private Integer sendAttempts;

    @Column(name = "verification_state")
    private Boolean verificationState;

    public void incrementAttemptsSend() {
        sendAttempts ++;
    }
}
