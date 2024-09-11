package com.gym.mail.adapter.service.providers;

import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailRegistrationService extends EmailSender {

    private final String nameMailProvider;
    private final String registrationSubject;

    protected EmailRegistrationService(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}") String nameMailProvider,
            @Value("${gym.mail.registration.subject}") String registrationSubject) {
        super(javaMailSender);
        this.nameMailProvider = nameMailProvider;
        this.registrationSubject = registrationSubject;
    }

    @Override
    public SimpleMailMessage getMessage(ValidMailConfirmationDto validMailConfirmationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nameMailProvider);
        message.setSubject(registrationSubject);
        message.setTo(validMailConfirmationDto.getMail());
        message.setText(
                "Hello, " +
                validMailConfirmationDto.getNickname() +
                "\nLink to confirm your registration: " +
                validMailConfirmationDto.getLink() +
                "\nLink will expire at " +
                validMailConfirmationDto.getExpirationDate());
        return message;
    }
}
