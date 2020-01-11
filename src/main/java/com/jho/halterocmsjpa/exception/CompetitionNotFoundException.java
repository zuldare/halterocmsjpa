package com.jho.halterocmsjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The competition does not exist")
public class CompetitionNotFoundException extends ValidationException {

    public CompetitionNotFoundException() {
        super();
    }
}
