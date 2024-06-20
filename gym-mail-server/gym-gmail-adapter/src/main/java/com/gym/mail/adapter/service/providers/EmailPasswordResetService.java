package com.gym.mail.adapter.service.providers;

import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailPasswordResetService extends EmailSender {

    private final String nameMailProvider;
    private final String passwordResetSubject;

    protected EmailPasswordResetService(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}") String nameMailProvider,
            @Value("${gym.mail.password.reset.subject}") String passwordResetSubject) {
        super(javaMailSender);
        this.nameMailProvider = nameMailProvider;
        this.passwordResetSubject = passwordResetSubject;
    }

    @Override
    public SimpleMailMessage getMessage(ValidMailConfirmationDto validMailConfirmationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nameMailProvider);
        message.setSubject(passwordResetSubject);
        message.setTo(validMailConfirmationDto.getMail());
        message.setText("TODO"); //TODO
        return message;
    }
}
