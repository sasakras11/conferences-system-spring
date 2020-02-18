package com.conferences.service.impl;

import com.conferences.exception.NoSuchElementInDatabaseException;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.ValidationException;
import com.conferences.repository.SpeechRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AbstractServiceTest {


    private static final String EXCEPTION_PATH = "path";
    @Mock
    private SpeechRepository speechRepository;

    @InjectMocks
    private SpeechServiceImpl speechService;

    @After
    public void resetMocks() {
        reset(speechRepository);
    }

    @Test(expected = OctalNumberParseException.class)
    public void whenValueIsNotOctalShouldBeParseException() {
        speechService.getParsedOctalNumberOrRedirect("nonOctal", EXCEPTION_PATH);
    }

    @Test(expected = NoSuchElementInDatabaseException.class)
    public void whenNoSuchElementInDatabaseShouldBeNoSuchElementInDatabaseException() {
           when(speechRepository.findById(any())).thenReturn(Optional.empty());
        speechService.findByIdIfPresentOrRedirect("1", speechRepository, EXCEPTION_PATH);
        verify(speechRepository).findById(any());
    }

    @Test(expected = OctalNumberParseException.class)
    public void whenValueHasLengthLessThenOneShouldBeParseException() {
        speechService.getParsedOctalNumberOrRedirect("", EXCEPTION_PATH);
    }

    @Test(expected = OctalNumberParseException.class)
    public void whenValueHasLengthMoreThenMillionShouldBeParseException() {
        speechService.getParsedOctalNumberOrRedirect("100000000", EXCEPTION_PATH);
    }

    @Test(expected = ValidationException.class)
    public void whenDateIsNotValidShouldBeValidationException() {
        speechService.getParsedDateOrRedirect("10/10/2020", EXCEPTION_PATH);
    }

    @Test(expected = ValidationException.class)
    public void whenDateLengthIsMoreThenTenShouldBeValidationException() {
        speechService.getParsedDateOrRedirect("10/10/20200", EXCEPTION_PATH);
    }

    @Test(expected = ValidationException.class)
    public void whenEndHourIsGreaterThenStartHourShouldBeValidationException(){
        speechService.getValidEndHourOrRedirect("2","1",EXCEPTION_PATH);


    }




}