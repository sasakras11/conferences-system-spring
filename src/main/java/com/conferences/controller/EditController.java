package com.conferences.controller;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import com.conferences.service.SpeechService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;


@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EditController extends AbstractController{


    private final UserBean userBean;
    private static final Logger LOGGER = LoggerFactory.getLogger(EditController.class);
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


    @RequestMapping(value = {"/edit"})
    public ModelAndView editConference(@RequestParam(value = "date") String date,
                                       @RequestParam(value = "name") String name, @RequestParam("conferenceId") String id) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        conferenceService.editConference(name, date, id);
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;
    }

    @RequestMapping(value = {"/editSpeechPage"})
    public ModelAndView editSpeechPage(@RequestParam("speechId") String speechId) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("speech",speechService.findById(speechId));
        modelAndView.addObject("pageNum", 1);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speechEdit");

        return modelAndView;

    }

    @RequestMapping(value = "/editSpeech")
    public ModelAndView editSpeech(@RequestParam(value = "topic", required = false) String topic,
                                   @RequestParam(value = "startHour", required = false) String startHour,
                                   @RequestParam(value = "endHour", required = false) String endHour,
                                   @RequestParam("id") String id,
                                   @RequestParam("suggestedTopic") String suggestedTopic) {

        ModelAndView modelAndView = new ModelAndView();
        Speech speech = speechService.editSpeechAndGet(topic, startHour, endHour, suggestedTopic, id);
        User user = userBean.getUser();
        modelAndView.addObject("speeches", speechService.findAllByConferenceId(speech.getConference().getConferenceId().toString()));
        modelAndView.addObject("userSpeeches", speechService.findAllByUserId(user.getUserId()));
        modelAndView.addObject("userName",user.getUsername());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");

        return modelAndView;


    }

    @RequestMapping(value = {"/ratingPage"})
    public ModelAndView ratingPage() {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("rating", ratingService.findAll());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");

        return modelAndView;
    }

    @RequestMapping(value = {"/editRating"})
    public ModelAndView editRating(@RequestParam("ratingId") String ratingId, @RequestParam("ratingMark") String ratingMark) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rating", ratingService.findAll());
        User user = userBean.getUser();
        ratingService.changeSpeakerRating(ratingId, ratingMark);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");

        return modelAndView;

    }
}
