package com.brazhnyk.epam_finalproject_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthenticationError extends Exception {

    public AuthenticationError() {
        super("unknown user");
    }

    public AuthenticationError(String errorMessage) {
        super(errorMessage);
    }
}
