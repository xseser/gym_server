package com.gym.mail.generator.service.time;

import java.time.LocalDateTime;

public interface ExpirationDateTimeProvider {

    LocalDateTime getExpirationDateTime();
}
