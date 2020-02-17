package com.conferences.config;

import com.conferences.entity.User;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.ValidationException;
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
    public ModelAndView handleException(OctalNumberParseException ex) {
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");

        switch (ex.getMessage()){

            case "rating" :
                modelAndView.addObject("rating", ratingService.findAll());
                break;
            case "conferences":
                modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
                modelAndView.addObject("pageNum", 1);
                break;

        }


        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/"+ex.getMessage());

        return modelAndView;

    }
    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleValidationException(ValidationException ex){
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.addObject("pageNum", 1);

        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/"+ex.getMessage());
        switch (ex.getMessage()){
            case "conferences":
                modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
                modelAndView.addObject("pageNum", 1);
                break;




        }

        return modelAndView;
    }



}
