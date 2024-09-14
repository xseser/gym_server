package com.gym.kafka.producer;

import com.gym.kafka.producer.mail.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducerWrapper implements GymKafkaProducer {

    private final KafkaTemplate<String, EventDto> kafkaTemplate;
    private final String kafkaMailGroup;

    public KafkaProducerWrapper(KafkaTemplate<String, EventDto> kafkaTemplate,
            @Value("${kafka.mail.group}") String kafkaMailGroup) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaMailGroup = kafkaMailGroup;
    }

    @Override
    public void sendMessage(EventDto mailDto) {
        log.info("sending message to kafka: {}", mailDto);
        CompletableFuture<SendResult<String, EventDto>> result = kafkaTemplate.send(kafkaMailGroup, mailDto);

        result.handle(((stringEventDtoSendResult, throwable) -> {
            if (throwable == null) {
                RecordMetadata metadata = stringEventDtoSendResult.getRecordMetadata();
                log.info("Message [{}] delivered with offset {}", mailDto, metadata.offset());
            } else {
                log.error("Unable to deliver message [{}]. {}", mailDto, throwable.getMessage());
            }
            return null; //TODO consider informing user when message was successfully sent to kafka queue
        }));
    }
}
