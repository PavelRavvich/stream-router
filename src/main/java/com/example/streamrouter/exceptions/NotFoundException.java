package com.example.streamrouter.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super(String.format("Record with id: %s is not found.", id));
    }
}
