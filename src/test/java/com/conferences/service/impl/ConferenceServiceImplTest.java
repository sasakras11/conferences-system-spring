package com.conferences.service.impl;

import com.conferences.entity.Conference;
import com.conferences.entity.ConferenceGroup;
import com.conferences.repository.ConferenceRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceServiceImplTest {


    List<Conference> finishedConferences = new ArrayList<>();
    List<Conference> comingConferences = new ArrayList<>();



    @Mock
    private ConferenceRepository conferenceRepository ;

    @InjectMocks
    private ConferenceServiceImpl conferenceService;


    @After
    public void resetMocks() {
        reset(conferenceRepository);
    }

    @Before
    public void init() {



    }

    @Test
     public  void getSameOrValidPageIfPageIsGreaterThenMax() {

        when(conferenceRepository.countByDateGreaterThan(any())).thenReturn(10);
        when(conferenceRepository.countByDateLessThan(any())).thenReturn(10);

        Assert.assertEquals(3, conferenceService.getSameOrValidPage(5, ConferenceGroup.COMING));
        verify(conferenceRepository).countByDateGreaterThan(any());
    }
    @Test
    public  void getSameOrValidPageIfPageIsNegative() {

        when(conferenceRepository.countByDateGreaterThan(any())).thenReturn(10);
        when(conferenceRepository.countByDateLessThan(any())).thenReturn(10);

        Assert.assertEquals(1, conferenceService.getSameOrValidPage(-1, ConferenceGroup.COMING));
        verify(conferenceRepository).countByDateGreaterThan(any());
    }


}