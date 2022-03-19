package com.saulotdr.apps.thermometer.exception;

public class RestValidationException extends RuntimeException {
    public RestValidationException(String message) {
        super(message);
    }
}
