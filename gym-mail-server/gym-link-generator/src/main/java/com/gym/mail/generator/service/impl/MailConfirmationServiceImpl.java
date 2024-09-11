package com.gym.mail.generator.service.impl;

import com.gym.mail.generator.dto.MailConfirmationRequestDto;
import com.gym.mail.generator.dto.ValidMailConfirmationDto;
import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.model.MailConfirmation;
import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.gym.mail.generator.service.LinkGenerator;
import com.gym.mail.generator.service.MailConfirmationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.response.gym.controller.url.UrlManagement.API_BASE;
import static com.response.gym.controller.url.UrlManagement.EMAIL_CONFIRMATION_ENDPOINT;

@Service
public class MailConfirmationServiceImpl implements MailConfirmationService {

    private final LinkGenerator linkGenerator;
    private final MailConfirmationRepository mailConfirmationRepository;
    private final Integer serverPort;
    private final String serverAddress;
    private final String serverProtocol;

    public MailConfirmationServiceImpl(
            LinkGenerator linkGenerator,
            MailConfirmationRepository mailConfirmationRepository,
            @Value("${server.port}") Integer serverPort,
            @Value("${server.address11}") String serverAddress,
            @Value("${server.protocol11}") String serverProtocol) {
        this.linkGenerator = linkGenerator;
        this.mailConfirmationRepository = mailConfirmationRepository;
        this.serverAddress = serverAddress;
        this.serverProtocol = serverProtocol;
        this.serverPort = serverPort;
    }

    @Override
    public ValidMailConfirmationDto generateNewConfirmationIfNotExist(MailConfirmationRequestDto confirmation, EmailType emailType) {
        MailConfirmation mailConfirmation =
                mailConfirmationRepository.findByMailAndLinkType(confirmation.getMail(), emailType)
                .filter(this::dropIfExpired)
                .orElseGet(() -> linkGenerator.generateAndSaveNewConfirmation(confirmation, emailType));

        return ValidMailConfirmationDto.builder()
                .mail(mailConfirmation.getMail())
                .nickname(mailConfirmation.getNickname())
                .link(resolverServerAddress() + mailConfirmation.getSecret())
                .expirationDate(mailConfirmation.getExpirationLinkTime())
                .build();
    }

    private boolean dropIfExpired(MailConfirmation mailConfirmation) {
        if (mailConfirmation.isLinkExpired()) {
            mailConfirmationRepository.delete(mailConfirmation);
            return true;
        }
        return false;
    }

    private String resolverServerAddress() {
        return serverProtocol + "://" +
               serverAddress + ":" +
               serverPort + "/" +
               API_BASE + EMAIL_CONFIRMATION_ENDPOINT;
    }

}
