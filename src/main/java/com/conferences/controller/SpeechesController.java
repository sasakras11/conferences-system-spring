package com.conferences.controller;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.SpeechService;
import com.conferences.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class SpeechesController {

    private final SpeechService speechService;
    private final ConferenceService conferenceService;
    private final UserService userService;
    private final UserBean userBean;

    @GetMapping(value = {"/speeches"})
    public ModelAndView speeches(@RequestParam("conferenceId") String conferenceId) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("userSpeeches",speechService.findAllByUserId(user.getUserId()));
        modelAndView.addObject("userName",user.getUsername());
        modelAndView.addObject("speeches", speechService.findAllByConferenceId(conferenceId));
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");

        return modelAndView;

    }

    @GetMapping(value = {"/reservePlace"})
    public ModelAndView reservePlace(@RequestParam("speechId") String speechId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();

          Speech speech = speechService.reservePlaceAndGet(speechId,user);
            modelAndView.addObject("speeches", speechService.findAllByConferenceId(speech.getConference().getConferenceId().toString()));
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");
            modelAndView.addObject("userSpeeches",speechService.findAllByUserId(user.getUserId()));


        return modelAndView;

    }
    @GetMapping(value = {"/userSpeeches"})
    public ModelAndView mySpeeches(){

        ModelAndView modelAndView  = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("userName",user.getUsername());
        modelAndView.addObject("speeches",speechService.findAllByUserId(user.getUserId()));
        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/userSpeeches");

        return modelAndView;



    }
    @GetMapping(value = {"/deleteReservation"})
     public ModelAndView  deleteReservation(@RequestParam("speechId") String speechId){
        ModelAndView modelAndView  = new ModelAndView();
        User user = userBean.getUser();
        userService.deleteReservation(user.getUserId().toString(),speechId);
        modelAndView.addObject("speeches",speechService.findAllByUserId(user.getUserId()));
        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/userSpeeches");
        return modelAndView;


    }
    @GetMapping(value = {"/finishedSpeeches"})
    public ModelAndView finishedSpeeches(@RequestParam("conferenceId") String conferenceId){
        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("speeches",speechService.findAllByConferenceId(conferenceId));
        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/finishedSpeeches");
        return modelAndView;
    }
}
