package com.gym.kafka.producer.mail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EventDto {
    private String nickname;
    private String mail;

    public EventDto(String nickname, String mail) {
        this.nickname = nickname;
        this.mail = mail;
    }
}
