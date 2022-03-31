package com.example.streamrouter.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(String.format("Bad request: %s", message));
    }
}
