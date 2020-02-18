package com.conferences.service.impl;

import com.conferences.entity.Speech;
import com.conferences.exception.ValidationException;
import com.conferences.repository.SpeechRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SpeechServiceImplTest {

    private static final String TOPIC = "topic";
    private static final String SUGGESTED_TOPIC = "suggested topic";
    private static final String NOT_VALID_TOPIC = "top";
    private static final String NOT_VALID_SUGGESTED_TOPIC = "top";
    private final Speech EMPTY_SPEECH = Speech.builder().build();

    private final Speech SPEAKER_SPEECH = Speech.builder().suggestedTopic(TOPIC)
            .suggestedTopic(SUGGESTED_TOPIC).build();

    private final Speech MODERATOR_AND_ADMIN_SPEECH = Speech.builder().startHour(1)
            .endHour(2).topic(TOPIC).suggestedTopic(SUGGESTED_TOPIC).build();

    @Mock
    private SpeechRepository speechRepository;

    @InjectMocks
    private SpeechServiceImpl speechService;


    @After
    public void resetMocks() {
        Mockito.reset(speechRepository);
    }

    @Test
    public void wheSpeakerEditingSpeechShouldBeChangedOnlySuggestedTopic() {
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
        Speech speech = speechService.editSpeechAndGet(null, null, null, SUGGESTED_TOPIC, "1");
        assertEquals(SPEAKER_SPEECH, speech);
        verify(speechRepository).findById(1);
    }

    @Test
    public void whenAdminOrModeratorEditingSpeechShouldBeChangedTopicAndHoursAndSuggestedTopic() {
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));

        Speech speech = speechService.editSpeechAndGet(TOPIC, "1", "2", SUGGESTED_TOPIC, "1");
        assertEquals(MODERATOR_AND_ADMIN_SPEECH, speech);

        verify(speechRepository).findById(1);

    }

    @Test(expected = ValidationException.class)
    public void whenStartHourIsNotValidShouldBeValidationException(){
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
        speechService.editSpeechAndGet(TOPIC, "25", "2", SUGGESTED_TOPIC, "1");
        verify(speechRepository).findById(1);


    }

    @Test(expected = ValidationException.class)
    public void whenEndHourIsNotValidShouldBeValidationException(){
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
        speechService.editSpeechAndGet(TOPIC, "22", "25", SUGGESTED_TOPIC, "1");
        verify(speechRepository).findById(1);

    }
    @Test(expected = ValidationException.class)
    public void whenTopicIsNotValidShouldBeValidationException(){
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
         speechService.editSpeechAndGet(NOT_VALID_TOPIC, "22", "23", SUGGESTED_TOPIC, "1");
        verify(speechRepository).findById(1);


    }
    @Test(expected = ValidationException.class)
    public void whenSuggestedTopicIsNotValidShouldBeValidationException(){
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
        speechService.editSpeechAndGet(TOPIC, "22", "23", NOT_VALID_SUGGESTED_TOPIC, "1");
        verify(speechRepository).findById(1);


    }
    @Test(expected = ValidationException.class)
       public void whenEndHourIsGreaterThenStartHourShouldBeValidationException(){
        when(speechRepository.findById(anyInt())).thenReturn(Optional.of(EMPTY_SPEECH));
        speechService.editSpeechAndGet(TOPIC,"21","20",SUGGESTED_TOPIC,"1");
        verify(speechRepository).findById(1);


    }




}