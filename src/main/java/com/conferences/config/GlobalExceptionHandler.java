package com.conferences.config;

import com.conferences.controller.UserBean;
import com.conferences.entity.User;
import com.conferences.exception.LoginCredentialsException;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.UserIsRegisteredException;
import com.conferences.exception.ValidationException;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final UserBean userBean;
    private final ConferenceService conferenceService;
    private final RatingService ratingService;


    @ExceptionHandler(OctalNumberParseException.class)
    public ModelAndView handleOctalNumberException(OctalNumberParseException ex) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        if (ex.getMessage().equals("rating")) {
            modelAndView.addObject("rating", ratingService.findAll());
        } else {
            modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
            modelAndView.addObject("pageNum", 1);
        }

        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/" + ex.getMessage());

        return modelAndView;

    }

    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleValidationException(ValidationException ex) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();

        if(ex.getMessage().equals("conferences")){
            modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
            modelAndView.addObject("pageNum", 1);
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/" + ex.getMessage());
        }
        else{
            modelAndView.setViewName(user.getRole().name().toLowerCase()+"/"+ex.getMessage());
        }


        return modelAndView;
    }

    @ExceptionHandler(UserIsRegisteredException.class)
    public ModelAndView userIsRegisteredExceptionHandler(UserIsRegisteredException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "user is already registered");
        modelAndView.setViewName(ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(LoginCredentialsException.class)
    public ModelAndView userCredentialsAreWrong(LoginCredentialsException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "user credentials are wrong");
        modelAndView.setViewName(ex.getMessage());
        return modelAndView;
    }


}
