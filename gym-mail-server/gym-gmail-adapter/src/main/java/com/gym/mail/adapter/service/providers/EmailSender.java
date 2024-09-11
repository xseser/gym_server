package com.gym.mail.adapter.service.providers;

import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public abstract class EmailSender {

    private final JavaMailSender javaMailSender;

    protected EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public abstract SimpleMailMessage getMessage(ValidMailConfirmationDto validMailConfirmationDto);

    public void send(ValidMailConfirmationDto validMailConfirmationDto) {
        SimpleMailMessage message = getMessage(validMailConfirmationDto);
        javaMailSender.send(message);
    }
}
