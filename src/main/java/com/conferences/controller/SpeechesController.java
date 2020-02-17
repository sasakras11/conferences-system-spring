package com.conferences.controller;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.SpeechService;
import com.conferences.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class SpeechesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechesController.class);
    private final SpeechService speechService;
    private final ConferenceService conferenceService;
    private final UserService userService;

    @GetMapping(value = {"/speeches"})
    public ModelAndView speeches(@RequestParam("conferenceId") int conferenceId) {

        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("userSpeeches",speechService.findAllByUserId(user.getUserId()));
        List<Speech> speeches = speechService.findAllByConference(conferenceId);
        modelAndView.addObject("speeches", speeches);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");

        return modelAndView;

    }

    @RequestMapping(value = {"/reservePlace"})
    public ModelAndView reservePlace(@RequestParam("speechId") String speechId) {
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        User user = (User) attr.getRequest().getSession().getAttribute("user");
        try {
            Speech speech = speechService.findById(speechId);
            List<User> visitors  = speech.getVisitors();
            visitors.add(user);
            speechService.save(speech);
            modelAndView.addObject("speeches", speechService.findAllByConference(speech.getConference().getConferenceId()));
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");
            modelAndView.addObject("userSpeeches",speechService.findAllByUserId(user.getUserId()));
            LOGGER.info(String.format("User %s reserved place in speech with id %s", user.getUsername(),speechId));


        } catch (Exception e) {
            LOGGER.warn(String.format("Exception when reserving place in speech with id %s", speechId));
            modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
            modelAndView.addObject("pageNumber", 1);

            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");



        }
        return modelAndView;

    }
    @GetMapping(value = {"/userSpeeches"})
    public ModelAndView mySpeeches(){

        ModelAndView modelAndView  = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");

        modelAndView.addObject("speeches",speechService.findAllByUserId(user.getUserId()));
        modelAndView.setViewName("userSpeeches");
        return modelAndView;



    }
    @RequestMapping(value = {"/deleteReservation"})
     public ModelAndView  deleteReservation(@RequestParam("speechId") String speechId){
        ModelAndView modelAndView  = new ModelAndView();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");


         userService.deleteReservation(user.getUserId().toString(),speechId);

        modelAndView.addObject("speeches",speechService.findAllByUserId(user.getUserId()));
        modelAndView.setViewName("userSpeeches");
        return modelAndView;


    }
}
