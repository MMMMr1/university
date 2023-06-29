package com.project.university.core.exception;

public class InvalidVersionException extends RuntimeException {
    public InvalidVersionException() {
    }
    public InvalidVersionException(String message) {
        super(message);
    }
}
