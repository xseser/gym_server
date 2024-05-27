package com.core.gym;

import com.response.gym.response.BadRequest;
import com.response.gym.response.Conflict;
import com.response.gym.response.MMTResponseCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.response.gym.controller.answer.UserAnswers.PASSWORDS_DOES_NOT_MATCH;

@ControllerAdvice
@Slf4j
public abstract class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity handleExceptionDuringValidation(IllegalArgumentException code) {
        ResponseEntity response = mapUserRegistrationCodes(Integer.parseInt(code.getMessage())).makeResponse();
        log.info("Sending new response to client: {}", response);
        return response;
    }

    public MMTResponseCreator mapUserRegistrationCodes(int code) {
        return switch (code) {
            case PASSWORDS_DOES_NOT_MATCH -> new Conflict(PASSWORDS_DOES_NOT_MATCH);
            default -> new BadRequest(code);
        };
    }
}
