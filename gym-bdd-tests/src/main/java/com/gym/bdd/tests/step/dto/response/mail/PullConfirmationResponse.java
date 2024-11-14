package com.gym.bdd.tests.step.dto.response.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PullConfirmationResponse {

    private String id;
    private String nickname;
    private String mail;
    private String secret;
    private String expirationLinkTime;
    private String linkType;
    private String emailStatus;
}
