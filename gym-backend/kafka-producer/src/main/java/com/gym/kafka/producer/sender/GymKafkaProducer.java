package com.gym.kafka.producer.sender;

import com.gym.kafka.producer.mail.EventDto;

import java.util.function.Consumer;

public interface GymKafkaProducer {

    void sendMessage(EventDto eventDto, Consumer<EventDto> onSuccess, Consumer<EventDto> onError);
}
