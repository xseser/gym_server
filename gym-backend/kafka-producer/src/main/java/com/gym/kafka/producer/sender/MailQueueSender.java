package com.gym.kafka.producer.sender;

import com.gym.kafka.producer.mail.EventDto;
import com.gym.kafka.producer.mail.UserRegistration;
import com.gym.kafka.producer.model.MailConfirmationDispatchQueue;
import com.gym.kafka.producer.repository.MailDispatchQueueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class MailQueueSender {

    private final MailDispatchQueueRepository mailDispatchQueueRepository;
    private final int maxAttemptsCount;
    private final GymKafkaProducer kafkaProducer;

    public MailQueueSender(
            MailDispatchQueueRepository mailDispatchQueueRepository,
            @Value("${kafka.max.attempts}") int maxAttemptsCount,
            GymKafkaProducer kafkaProducer) {
        this.mailDispatchQueueRepository = mailDispatchQueueRepository;
        this.maxAttemptsCount = maxAttemptsCount;
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRateString = "${kafka.mail.queue.rate.ms}")
    public void scheduledSending() {
        log.info("scheduled job triggerred");
        mailDispatchQueueRepository.findAll()
                .forEach(this::broadcastMessage);
    }

    private void broadcastMessage(MailConfirmationDispatchQueue mailConfirmationDispatchQueue) {
        log.info("broadcasting mail confirmation dispatch queue. Mail with id: {}", mailConfirmationDispatchQueue.getId());
        EventDto eventDto = mapToMail(mailConfirmationDispatchQueue);
        kafkaProducer.sendMessage(
                eventDto,
                onSuccess -> processOnSuccess(mailConfirmationDispatchQueue),
                onError -> processOnError(mailConfirmationDispatchQueue));
    }

    private static EventDto mapToMail(MailConfirmationDispatchQueue mailConfirmationDispatchQueue) {
        return new UserRegistration(
                mailConfirmationDispatchQueue.getNickname(),
                mailConfirmationDispatchQueue.getMail(),
                mailConfirmationDispatchQueue.getVerificationState());
    }

    private void processOnSuccess(MailConfirmationDispatchQueue mailConfirmationDispatchQueue) {
        log.info("Mail with id: {} successfully processed", mailConfirmationDispatchQueue.getId());
        mailDispatchQueueRepository.delete(mailConfirmationDispatchQueue);
    }

    private void processOnError(MailConfirmationDispatchQueue mailConfirmationDispatchQueue) {
        if (mailConfirmationDispatchQueue.getSendAttempts() >= maxAttemptsCount) {
            log.info("Mail with id: {} was not processed successfully. Counter exceeded. Deleting event",
                    mailConfirmationDispatchQueue.getId());
            mailDispatchQueueRepository.delete(mailConfirmationDispatchQueue);
        }
        log.info("Mail with id: {} was not processed successfully. Incrementing counter: {}",
                mailConfirmationDispatchQueue.getId(),
                mailConfirmationDispatchQueue.getSendAttempts());
        mailConfirmationDispatchQueue.incrementAttemptsSend();
        mailDispatchQueueRepository.save(mailConfirmationDispatchQueue);
    }
}
