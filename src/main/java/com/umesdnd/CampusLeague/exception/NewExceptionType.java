package com.umesdnd.CampusLeague.exception;

import org.springframework.http.HttpStatus;

public class NewExceptionType extends RuntimeException{

    private final HttpStatus status;

    public NewExceptionType(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
