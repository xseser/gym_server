package com.gym.mail.generator.repository;

import com.gym.mail.generator.model.EmailType;
import com.gym.mail.generator.model.MailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MailConfirmationRepository extends JpaRepository<MailConfirmation, UUID> {

    Optional<MailConfirmation> findByMailAndLinkType(String mail, EmailType emailType);

    List<MailConfirmation> findAllByNickname(String nickname);

    Optional<MailConfirmation> findMailConfirmationBySecret(String secret);
}
