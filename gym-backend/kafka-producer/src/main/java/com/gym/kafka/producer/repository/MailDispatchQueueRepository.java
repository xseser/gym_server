package com.gym.kafka.producer.repository;

import com.gym.kafka.producer.model.MailConfirmationDispatchQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailDispatchQueueRepository extends JpaRepository<MailConfirmationDispatchQueue, UUID> {
}
