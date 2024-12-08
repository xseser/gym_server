package com.gym.bdd.tests.step.dto.response.confirmation;

import com.gym.bdd.tests.step.dto.request.RequestMarker;
import com.gym.bdd.tests.step.dto.response.ResponseMarker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PullConfirmationResponse implements ResponseMarker, RequestMarker {

    private String id;
    private String nickname;
    private String mail;
    private String secret;
    private String expirationLinkTime;
    private String linkType;
    private String emailStatus;
}
