package com.conferences.service;

import com.conferences.exception.NotValidPasswordException;
import com.conferences.exception.NotValidUsernameException;
import org.springframework.stereotype.Component;


@Component
public class Validator {

    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,}";
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

    public void validate(String username, String password) {
        validateUsername(username);
        validatePassword(password);
    }

    private void validateUsername(String str) {
        if (!str.matches(USERNAME_PATTERN)) {
            throw new NotValidUsernameException("registration");
        }
    }

    private void validatePassword(String str) {
        if (!str.matches(PASSWORD_PATTERN)) {
            throw new NotValidPasswordException("registration");
        }
    }
}
