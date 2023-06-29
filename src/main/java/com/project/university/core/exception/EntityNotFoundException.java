package com.project.university.core.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
