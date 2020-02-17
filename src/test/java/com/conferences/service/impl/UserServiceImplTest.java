package com.conferences.service.impl;

import com.conferences.entity.Role;
import com.conferences.entity.User;
import com.conferences.exception.LoginCredentialsException;
import com.conferences.exception.UserIsRegisteredException;
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

    @Test(expected = LoginCredentialsException.class)
    public void whenPasswordIsWrongShouldBeException() {
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(USER));
        when(passwordEncryptor.matches(any(), any())).thenReturn(false);

        User user = service.login(USERNAME, PASSWORD);

        verify(userRepository).findUserByUsername(eq(USERNAME));
        verify(passwordEncryptor).matches(any(), any());

    }

    @Test(expected = LoginCredentialsException.class)
    public void whenThereIsNoSuchUserInDatabaseShouldBeException() {
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.empty());
        when(passwordEncryptor.matches(any(), any())).thenReturn(true);

        User user = service.login(USERNAME, PASSWORD);


        verify(userRepository).findUserByUsername(eq(USERNAME));
        when(passwordEncryptor.matches(any(),any()));
    }

    @Test
    public void whenCredentialsAreVerifiedLoginShouldReturnTrue() {
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(USER));
        when(passwordEncryptor.matches(any(), any())).thenReturn(true);
      User user = service.login(USERNAME, PASSWORD);

        Assert.assertEquals(USER,user);

        verify(userRepository).findUserByUsername(eq(USERNAME));
        verify(passwordEncryptor).matches(any(), any());

    }

    @Test
    public void userShouldRegisterIfHeIsValidatedAndThereIsNoSuchUserInDatabase() {
        doNothing().when(validator).validate(any(), any());
        when(userRepository.findUserByUsername(any())).thenReturn(Optional.empty());


        Assert.assertEquals(USER, service.register(USERNAME, PASSWORD));

        verify(userRepository).findUserByUsername(eq(USERNAME));
    }

    @Test(expected = UserIsRegisteredException.class)
    public void ifUserIsAlreadyInDatabaseShouldBeException(){
        doNothing().when(validator).validate(USERNAME,PASSWORD);
        when(userRepository.findUserByUsername(USERNAME)).thenReturn(Optional.of(USER));

        service.register(USERNAME,PASSWORD);

        verify(userRepository).findUserByUsername(USERNAME);

    }








}