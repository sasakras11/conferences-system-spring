package com.conferences.exception;

public class NoSuchElementInDatabaseException extends RuntimeException {
    public NoSuchElementInDatabaseException(String message) {
        super(message);
    }
}
