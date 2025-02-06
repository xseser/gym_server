package com.gym.kafka.producer.sender;

import com.gym.kafka.producer.mail.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

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
    public void sendMessage(EventDto mailDto, Consumer<EventDto> onSuccess, Consumer<EventDto> onError) {
        log.info("sending message to kafka: {}", mailDto);

        kafkaTemplate
                .send(kafkaMailGroup, mailDto)
                .handle((stringEventDtoSendResult, throwable) -> {
            if (throwable == null) {
                RecordMetadata metadata = stringEventDtoSendResult.getRecordMetadata();
                log.info("Message [{}] delivered with metadata: {}", mailDto, metadata);
                onSuccess.accept(mailDto);
            } else {
                log.error("Unable to deliver message [{}]. {}", mailDto, throwable.getMessage());
                onError.accept(mailDto);
            }
            return mailDto; //TODO consider informing user when message was successfully sent to kafka queue
        });
    }
}
