package com.gym.mail.generator.service.impl;

import com.gym.mail.generator.dto.MailConfirmationRequestDto;
import com.gym.mail.generator.model.EmailStatus;
import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.model.MailConfirmation;
import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.gym.mail.generator.service.LinkGenerator;
import com.gym.mail.generator.service.time.ExpirationDateTimeProvider;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.response.gym.controller.url.UrlManagement.API_BASE;
import static com.response.gym.controller.url.UrlManagement.EMAIL_CONFIRMATION_ENDPOINT;

@Slf4j
@Service
public class LinkGeneratorServiceImpl implements LinkGenerator {

    private final MailConfirmationRepository mailConfirmationRepository;
    private final ExpirationDateTimeProvider expirationDateTimeProvider;
    private final Integer serverPort;
    private final String serverAddress;
    private final String serverProtocol;

    public LinkGeneratorServiceImpl(
            MailConfirmationRepository mailConfirmationRepository,
            ExpirationDateTimeProvider expirationDateTimeProvider,
            @Value("${server.port}") Integer serverPort,
            @Value("${server.address11}") String serverAddress,
            @Value("${server.protocol11}") String serverProtocol) {
        this.mailConfirmationRepository = mailConfirmationRepository;
        this.expirationDateTimeProvider = expirationDateTimeProvider;
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
        this.serverProtocol = serverProtocol;
    }

    @Transactional
    @Override
    public MailConfirmation generateAndSaveNewConfirmation(MailConfirmationRequestDto mailConfirmationRequestDto, EmailType emailType) {
        MailConfirmation mailConfirmation = MailConfirmation.builder()
                .id(UUID.randomUUID())
                .nickname(mailConfirmationRequestDto.getNickname())
                .secret(generateRandom())
                .mail(mailConfirmationRequestDto.getMail())
                .expirationLinkTime(expirationDateTimeProvider.getExpirationDateTime())
                .linkType(emailType)
                .emailStatus(EmailStatus.CREATED)
                .build();
        return mailConfirmationRepository.save(mailConfirmation);
    }

    private String generateConfirmationLink() {
        return resolverServerAddress() + API_BASE + EMAIL_CONFIRMATION_ENDPOINT + generateRandom();
    }

    private String resolverServerAddress() {
        return serverProtocol + "://" +
               serverAddress + ":" +
               serverPort + "/";
    }

    private String generateRandom() {
        return RandomStringUtils.randomNumeric(50);
    }
}
