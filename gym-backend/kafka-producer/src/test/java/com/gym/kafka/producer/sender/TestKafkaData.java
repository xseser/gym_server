package com.gym.kafka.producer.sender;

import com.gym.kafka.producer.mail.UserRegistration;
import com.gym.kafka.producer.model.MailConfirmationDispatchQueue;

import java.util.UUID;

public class TestKafkaData {

    private final UUID id;
    private final String nickname;
    private final String mail;
    private int sendAttempts;
    private final boolean verificationState;

    public TestKafkaData(String nickname, String mail, int sendAttempts, boolean verificationState) {
        this.id = UUID.randomUUID();
        this.nickname = nickname;
        this.mail = mail;
        this.sendAttempts = sendAttempts;
        this.verificationState = verificationState;
    }

    public TestKafkaData(int sendAttempts) {
        this.id = UUID.randomUUID();
        this.nickname = "nickname";
        this.mail = "mail";
        this.sendAttempts = sendAttempts;
        this.verificationState = true;
    }

    MailConfirmationDispatchQueue getMailConfirmationDispatchQueue() {
        return new MailConfirmationDispatchQueue(id, mail, nickname, sendAttempts, verificationState);
    }

    UserRegistration getUserRegistration() {
        return new UserRegistration(nickname, mail, verificationState);
    }

    void incrementSendAttempts() {
        sendAttempts++;
    }
}
