package com.jho.halterocmsjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The competition already exists")
public class CompetitionAlreadyExistsException extends ValidationException {

    public CompetitionAlreadyExistsException() {
        super();
    }
}
