package com.gym.kafka.producer.sender;

import com.gym.kafka.producer.mail.EventDto;
import com.gym.kafka.producer.model.MailConfirmationDispatchQueue;
import com.gym.kafka.producer.repository.MailDispatchQueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailQueueSenderTest {

    @Mock
    private MailDispatchQueueRepository mailDispatchQueueRepository;

    @Mock
    private GymKafkaProducer kafkaProducer;

    private final int maxAttemptsCount = 3;

    private MailQueueSender mailQueueSender;

    @BeforeEach
    public void beforeEach() {
        mailQueueSender = new MailQueueSender(mailDispatchQueueRepository, maxAttemptsCount, kafkaProducer);
    }

    @Test
    void shouldBroadcastMessagesFromQueue() {
        // Given
        TestKafkaData data = new TestKafkaData(0);
        MailConfirmationDispatchQueue queueItem = data.getMailConfirmationDispatchQueue();
        when(mailDispatchQueueRepository.findAll()).thenReturn(List.of(queueItem));

        // When
        mailQueueSender.scheduledSending();

        // Then
        verify(kafkaProducer).sendMessage(any(EventDto.class), any(), any());
    }

    @Test
    void shouldRemoveItemOnSuccess() {
        // Given
        TestKafkaData data = new TestKafkaData(0);
        MailConfirmationDispatchQueue queueItem = data.getMailConfirmationDispatchQueue();
        when(mailDispatchQueueRepository.findAll()).thenReturn(List.of(queueItem));

        doAnswer(invocation -> {
            Consumer<EventDto> onSuccess = invocation.getArgument(1);
            onSuccess.accept(data.getUserRegistration());
            return null;
        }).when(kafkaProducer).sendMessage(any(EventDto.class), any(), any());

        // When
        mailQueueSender.scheduledSending();

        // Then
        verify(mailDispatchQueueRepository).delete(queueItem);
    }

    @Test
    void shouldBumpCounterOnErrorIfMaxAttemptsNotExceeded() {
        // Given
        TestKafkaData data = new TestKafkaData(0);
        MailConfirmationDispatchQueue queueItem = data.getMailConfirmationDispatchQueue();
        when(mailDispatchQueueRepository.findAll()).thenReturn(List.of(queueItem));

        doAnswer(invocation -> {
            Consumer<EventDto> onError = invocation.getArgument(2);
            onError.accept(data.getUserRegistration());
            return null;
        }).when(kafkaProducer).sendMessage(any(EventDto.class), any(), any());

        // When
        mailQueueSender.scheduledSending();

        // Then
        data.incrementSendAttempts();
        verify(mailDispatchQueueRepository).save(eq(data.getMailConfirmationDispatchQueue()));
    }

    @Test
    void shouldNotBumpCounterOnErrorIfMaxAttemptsExceeded() {
        // Given
        TestKafkaData data = new TestKafkaData(3);
        MailConfirmationDispatchQueue queueItem = data.getMailConfirmationDispatchQueue();
        when(mailDispatchQueueRepository.findAll()).thenReturn(List.of(queueItem));

        doAnswer(invocation -> {
            Consumer<EventDto> onError = invocation.getArgument(2);
            onError.accept(data.getUserRegistration());
            return null;
        }).when(kafkaProducer).sendMessage(any(EventDto.class), any(), any());

        // When
        mailQueueSender.scheduledSending();

        // Then
        verify(mailDispatchQueueRepository).delete(queueItem);
    }

    @Test
    void shouldSendManyMailsIfThereAreManyPending() {
        // Given
        TestKafkaData data = new TestKafkaData(0);
        MailConfirmationDispatchQueue queueItem1 = data.getMailConfirmationDispatchQueue();

        TestKafkaData data2 = new TestKafkaData("nick", "mail", 0, true);
        MailConfirmationDispatchQueue queueItem2 = data2.getMailConfirmationDispatchQueue();
        when(mailDispatchQueueRepository.findAll()).thenReturn(List.of(queueItem1, queueItem2));

        // When
        mailQueueSender.scheduledSending();

        // Then
        verify(kafkaProducer, times(2))
                .sendMessage(any(EventDto.class), any(), any());
    }
}
