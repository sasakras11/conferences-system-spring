package com.conferences.exception;

public class UserIsRegisteredException extends RuntimeException {
    public UserIsRegisteredException(String message) {
        super(message);
    }
}
