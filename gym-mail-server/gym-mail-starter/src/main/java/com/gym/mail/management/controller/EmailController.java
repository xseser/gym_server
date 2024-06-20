package com.gym.mail.management.controller;

import com.gym.mail.management.service.LinkAccepterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.response.gym.controller.url.UrlManagement.API_BASE;
import static com.response.gym.controller.url.UrlManagement.EMAIL_CONFIRMATION_ENDPOINT;
import static com.response.gym.controller.url.UrlManagement.EMAIL_CONFIRMATION_PATH_VARIABLE;

@Slf4j
@RestController
public class EmailController {

    private final LinkAccepterService linkAccepterService;

    public EmailController(LinkAccepterService linkAccepterService) {
        this.linkAccepterService = linkAccepterService;
    }

    @RequestMapping(method = RequestMethod.GET, value = API_BASE + EMAIL_CONFIRMATION_ENDPOINT + EMAIL_CONFIRMATION_PATH_VARIABLE)
    public ResponseEntity acceptMail(@PathVariable String confirmation) {
        log.info("received request for action confirmation with data: {}", confirmation);
        return linkAccepterService.acceptConfirmation(confirmation)
                .makeResponse();
    }
}
