package com.conferences.config;

import com.conferences.controller.UserBean;
import com.conferences.entity.Role;
import com.conferences.entity.User;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.ValidationException;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {



    private static final User USER = User.builder().role(Role.ADMIN).build();

    private static final String RATING = "rating";
    private static final String RATING_PATH = "admin/rating";
    private static final String CONFERENCES = "conferences";
    private static final String CONFERENCES_PATH = "admin/conferences";



    @Mock
    private UserBean userBean;
    @Mock
    private ConferenceService conferenceService;
    @Mock
    private RatingService ratingService;

    @InjectMocks
     private GlobalExceptionHandler exceptionHandler;

    @After
    public void init(){
        Mockito.reset(conferenceService,userBean,ratingService);
    }

    @Test
    public void OctalExceptionHandlerShouldReturnRatingIfExceptionMessageIsRating() {
        Mockito.when(userBean.getUser()).thenReturn(USER);
        Mockito.when(ratingService.findAll()).thenReturn(new ArrayList<>());

        ModelAndView mv = exceptionHandler.handleOctalNumberException(new OctalNumberParseException(RATING));
        Assert.assertEquals(RATING_PATH,mv.getViewName());
    }
    @Test
    public void OctalExceptionHandlerShouldReturnConferencesIfExceptionParamIsNotRating() {
        Mockito.when(userBean.getUser()).thenReturn(USER);
        Mockito.when(conferenceService.findComingConferences("1")).thenReturn(new ArrayList<>());

        ModelAndView mv = exceptionHandler.handleOctalNumberException(new OctalNumberParseException(CONFERENCES));
        Assert.assertEquals(CONFERENCES_PATH,mv.getViewName());
    }
@Test
    public void ValidationExceptionHandlerShouldReturnConferencesIfExceptionMessageIsConferences(){
        Mockito.when(userBean.getUser()).thenReturn(USER);
        Mockito.when(conferenceService.findComingConferences("1")).thenReturn(new ArrayList<>());

        ModelAndView mv = exceptionHandler.handleValidationException(new ValidationException(CONFERENCES));
        Assert.assertEquals(CONFERENCES_PATH,mv.getViewName());
    }
@Test
    public void ValidationExceptionHandlerShouldReturnSamePagIfeExceptionMessageIsRating(){
        Mockito.when(userBean.getUser()).thenReturn(USER);

        ModelAndView mv = exceptionHandler.handleValidationException(new ValidationException(RATING));
        Assert.assertEquals(RATING_PATH,mv.getViewName());


    }


}