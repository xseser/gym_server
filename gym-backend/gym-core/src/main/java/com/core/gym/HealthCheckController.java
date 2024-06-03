package com.core.gym;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.response.gym.controller.url.UrlManagement.API_BASE;
import static com.response.gym.controller.url.UrlManagement.HEALTH;

@RestController
public class HealthCheckController {

    @RequestMapping(method = RequestMethod.GET, value = API_BASE + HEALTH)
    public ResponseEntity healthCheck() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
