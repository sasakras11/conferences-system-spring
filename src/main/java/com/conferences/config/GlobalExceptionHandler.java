package com.conferences.config;

import com.conferences.controller.UserBean;
import com.conferences.entity.User;
import com.conferences.exception.*;
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
        if (ex.getMessage().equals("conferences")) {
            modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
            modelAndView.addObject("pageNum", 1);
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/" + ex.getMessage());
        } else if (ex.getMessage().equals("registration")) {
            modelAndView.addObject("error", "");
            modelAndView.setViewName(ex.getMessage());
            return modelAndView;
        } else {
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/" + ex.getMessage());
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

    @ExceptionHandler(NotValidUsernameException.class)
    public ModelAndView notValidUsername(NotValidUsernameException ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "username should be at least 3 characters long and \n" +
                "contain only latin symbols and numbers.");
        modelAndView.setViewName(ex.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(NotValidPasswordException.class)
    public ModelAndView notValidUsername(NotValidPasswordException ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "password should be at least 8 characters long and \n" +
                "contain only latin symbols and numbers.");
        modelAndView.setViewName(ex.getMessage());

        return modelAndView;
    }
}
