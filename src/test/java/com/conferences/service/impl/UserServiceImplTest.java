package com.conferences.service.impl;

import com.conferences.entity.Role;
import com.conferences.entity.User;
import com.conferences.exception.ValidationException;
import com.conferences.repository.UserRepository;
import com.conferences.service.Encryptor;
import com.conferences.service.Validator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";

    private static final User USER =
            User.builder()
                    .role(Role.VISITOR)
                    .username(USERNAME)
                    .build();
    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;
    @Mock
    private Encryptor passwordEncryptor;

    @InjectMocks
    private UserServiceImpl service;

    @After
    public void resetMocks() {
        reset(userRepository, validator, passwordEncryptor);
    }

    @Test
    public void whenThereISNotSuchUserLoginShouldReturnOptionalEmpty() {
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.empty());
        Optional<User> user = service.login(USERNAME, PASSWORD);

        Assert.assertFalse(user.isPresent());

        verify(userRepository).findUserByUsername(eq(USERNAME));
        verifyZeroInteractions(passwordEncryptor);

    }

    @Test
    public void whenUserPasswordInIncorrectUseShouldNotLogin(){ //TODO language /pagination url = handle /// test it
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(USER));
        when(passwordEncryptor.matches(any(),any())).thenReturn(false);

        Optional<User> user = service.login(USERNAME, PASSWORD);


        Assert.assertFalse(user.isPresent());
        verify(userRepository).findUserByUsername(eq(USERNAME));
    }

    @Test
    public void whenCredentialsAreVerifiedLoginShouldReturnTrue() {

        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(USER));
        when(passwordEncryptor.matches(any(), any())).thenReturn(true);
        Optional<User> user = service.login(USERNAME, PASSWORD);

        Assert.assertTrue(user.isPresent());

        verify(userRepository).findUserByUsername(eq(USERNAME));

    }

    @Test
    public void userShouldRegisterIfHeIsValidatedAndThereIsNoSuchUserInDatabase() {
        doNothing().when(validator).validate(any(), any());
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.empty());


        Assert.assertEquals(USER, service.register(USERNAME, PASSWORD));

        verify(userRepository).findUserByUsername(eq(USERNAME));
    }

    @Test(expected = ValidationException.class)
    public void userShouldNotRegisterIfUserWithSuchUsernameExists() {
        doNothing().when(validator).validate(any(), any());
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.of(USER));

        service.register(USERNAME, PASSWORD);

        verify(validator).validate(USERNAME, PASSWORD);
        verify(userRepository).findUserByUsername(eq(USERNAME));
    }


}