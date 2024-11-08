package com.gym.mail.management.controller;

import com.gym.mail.generator.model.MailConfirmation;
import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.gym.mail.management.controller.dto.MockedMailConfirmation;
import com.response.gym.response.Ok;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.response.gym.controller.url.UrlManagement.API_INTERNAL;

@Slf4j
@Profile("mock")
@RestController
public class MockController {

    private final MailConfirmationRepository mailConfirmationRepository;

    public MockController(MailConfirmationRepository mailConfirmationRepository) {
        this.mailConfirmationRepository = mailConfirmationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = API_INTERNAL + "mails")
    public ResponseEntity getPendingMails(@RequestParam("nickname") String nickname) {
        log.info("received mocked request to get pending emails for user: {}", nickname);
        List<MockedMailConfirmation> mockedMailConfirmations = mailConfirmationRepository
                .findAllByNickname(nickname)
                .stream()
                .map(MockedMailConfirmation::new)
                .toList();

        return new Ok(mockedMailConfirmations).makeResponse();
    }
}
