package com.gym.mail.management.service.impl;

import com.gym.mail.generator.model.EmailStatus;
import com.gym.mail.generator.model.MailConfirmation;
import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.gym.mail.management.dto.ConfirmationDto;
import com.gym.mail.management.factory.ActionByTypeResolver;
import com.gym.mail.management.service.LinkAccepterService;
import com.response.gym.response.MMTResponseCreator;
import com.response.gym.response.NotFound;
import cyclops.control.Either;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.response.gym.controller.answer.MailAnswers.INVALID_MAIL_CONFIRMATION;

@Slf4j
@Service
public class LinkAccepterServiceImpl implements LinkAccepterService {

    private final MailConfirmationRepository mailConfirmationRepository;
    private final ActionByTypeResolver actionByTypeResolver;

    public LinkAccepterServiceImpl(
            MailConfirmationRepository mailConfirmationRepository,
            ActionByTypeResolver actionByTypeResolver) {
        this.mailConfirmationRepository = mailConfirmationRepository;
        this.actionByTypeResolver = actionByTypeResolver;
    }

    @Override
    public MMTResponseCreator acceptConfirmation(String confirmation) {
        log.info("secret: {}", confirmation);
        return mailConfirmationRepository.findMailConfirmationBySecret(confirmation)
                .filter(MailConfirmation::isLinkExpired)
                .<Either<MMTResponseCreator, MailConfirmation>>map(Either::right)
                .orElse(Either.left(new NotFound(INVALID_MAIL_CONFIRMATION)))
                .peek(this::setMailStatusToSuspended)
                .map(this::mapToConfirmationDto)
                .fold(
                        response -> response,
                        confirmationDto -> actionByTypeResolver.resolveActionClass(confirmationDto.getLinkType())
                                .perform(confirmationDto));
    }

    private ConfirmationDto mapToConfirmationDto(MailConfirmation confirmation) {
        return ConfirmationDto.builder()
                .id(confirmation.getId())
                .expirationLinkTime(confirmation.getExpirationLinkTime())
                .linkType(confirmation.getLinkType())
                .status(confirmation.getEmailStatus())
                .secret(confirmation.getSecret())
                .nickname(confirmation.getNickname())
                .mail(confirmation.getMail())
                .build();
    }

    @Transactional
    public void setMailStatusToSuspended(MailConfirmation mailConfirmation) {
        mailConfirmation.setEmailStatus(EmailStatus.SUSPENDED);
        mailConfirmationRepository.save(mailConfirmation);
    }
}
