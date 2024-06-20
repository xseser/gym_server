package com.gym.mail.adapter.service.providers;

import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailAdvertisementService extends EmailSender {

    private final String nameMailProvider;
    private final String advertisementSubject;

    protected EmailAdvertisementService(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}") String nameMailProvider,
            @Value("${gym.mail.advertisement.subject}") String advertisementSubject) {
        super(javaMailSender);
        this.nameMailProvider = nameMailProvider;
        this.advertisementSubject = advertisementSubject;
    }

    @Override
    public SimpleMailMessage getMessage(ValidMailConfirmationDto validMailConfirmationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(nameMailProvider);
        message.setSubject(advertisementSubject);
        message.setTo(validMailConfirmationDto.getMail());
        message.setText("TODO"); //TODO
        return message;
    }
}
