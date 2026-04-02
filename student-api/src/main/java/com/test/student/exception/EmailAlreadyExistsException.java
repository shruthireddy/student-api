package com.test.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException {

    // This is the constructor your Service is looking for!
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}