package com.gym.kafka.producer;

import com.gym.kafka.producer.mail.EventDto;

public interface GymKafkaProducer {

    void sendMessage(EventDto eventDto);
}
