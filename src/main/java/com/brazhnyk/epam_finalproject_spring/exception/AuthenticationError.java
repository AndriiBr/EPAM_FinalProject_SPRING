package com.brazhnyk.epam_finalproject_spring.exception;

public class AuthenticationError extends Exception {

    public AuthenticationError() {
        super("unknown user");
    }

    public AuthenticationError(String errorMessage) {
        super(errorMessage);
    }
}
