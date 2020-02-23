package com.conferences.controller;

import com.conferences.entity.Role;
import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import com.conferences.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EditController {


    private final UserBean userBean;
    private final ConferenceService conferenceService;
    private final SpeechService speechService;
    private final RatingService ratingService;


    @GetMapping(value = {"/editConferencePage"})
    public ModelAndView editConferencePage(@RequestParam("id") String id) {

        User user = userBean.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("conference", conferenceService.findById(id));
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferenceEdit");

        return modelAndView;
    }


    @GetMapping(value = {"/edit"})
    public ModelAndView editConference(@RequestParam(value = "date") String date,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam("conferenceId") String id) {

        User user = userBean.getUser();

        ModelAndView modelAndView = new ModelAndView();
        if (user.getRole() == Role.ADMIN || user.getRole() == Role.MODERATOR) {
            conferenceService.editConference(name, date, id);
        }
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;
    }

    @GetMapping(value = {"/editSpeechPage"})
    public ModelAndView editSpeechPage(@RequestParam("speechId") String speechId) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("speech", speechService.findById(speechId));
        modelAndView.addObject("pageNum", 1);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speechEdit");

        return modelAndView;
    }

    @GetMapping(value = "/editSpeech")
    public ModelAndView editSpeech(@RequestParam(value = "topic", required = false) String topic,
                                   @RequestParam(value = "startHour", required = false) String startHour,
                                   @RequestParam(value = "endHour", required = false) String endHour,
                                   @RequestParam(value = "suggestedTopic", required = false) String suggestedTopic,
                                   @RequestParam("id") String id) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();

        if (user.getRole() == Role.ADMIN || user.getRole() == Role.MODERATOR) {
            Speech speech = speechService.editSpeechAndGet(topic, startHour, endHour, suggestedTopic, id);
            modelAndView.addObject("speeches", speechService.findAllByConferenceId(speech.getConference().getConferenceId().toString()));
        }
        modelAndView.addObject("userSpeeches", speechService.findAllByUserId(user.getUserId()));
        modelAndView.addObject("userName", user.getUsername());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");

        return modelAndView;
    }

    @GetMapping(value = {"/ratingPage"})
    public ModelAndView ratingPage() {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("rating", ratingService.findAll());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");

        return modelAndView;
    }

    @GetMapping(value = {"/editRating"})
    public ModelAndView editRating(@RequestParam("ratingId") String ratingId, @RequestParam("ratingMark") String ratingMark) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        if (user.getRole() == Role.ADMIN) {
            ratingService.changeSpeakerRating(ratingId, ratingMark);
        }
            modelAndView.addObject("rating", ratingService.findAll());
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");

        return modelAndView;
    }
}
