package com.gym.mail.generator.service.time;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("production")
public class ProductionExpirationTime implements ExpirationDateTimeProvider {

    private final String expirationTimeInMinutes;

    public ProductionExpirationTime(@Value("${gym.mail.link.expiration.time.in.minutes}") String time) {
        this.expirationTimeInMinutes = time;
    }

    @Override
    public LocalDateTime getExpirationDateTime() {
        return LocalDateTime.now().plusMinutes(Integer.parseInt(expirationTimeInMinutes));
    }
}
