package com.gym.mail.management.controller;

import com.gym.mail.generator.model.MailConfirmation;
import com.gym.mail.generator.repository.MailConfirmationRepository;
import com.response.gym.response.Ok;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.response.gym.controller.url.UrlManagement.API_INTERNAL;

@Profile("mock")
@RestController
public class MockController {

    private final MailConfirmationRepository mailConfirmationRepository;

    public MockController(MailConfirmationRepository mailConfirmationRepository) {
        this.mailConfirmationRepository = mailConfirmationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = API_INTERNAL + "mails")
    public ResponseEntity getPendingMails(@RequestParam("nickname") String nickname) {
        List<MailConfirmation> mailConfirmations = mailConfirmationRepository.findAllByNickname(nickname);
        return new Ok(mailConfirmations).makeResponse();
    }
}
