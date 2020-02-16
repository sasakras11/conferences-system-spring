package com.conferences.config;

import com.conferences.entity.ConferenceGroup;
import com.conferences.entity.User;
import com.conferences.exception.NoSuchElementInDatabaseException;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private final ConferenceService conferenceService;
    private final RatingService ratingService;



    @ExceptionHandler(OctalNumberParseException.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("rating", ratingService.findAll());

        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/"+ex.getMessage());

        return modelAndView;

    }



}
