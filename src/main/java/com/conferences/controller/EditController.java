package com.conferences.controller;


import com.conferences.entity.Conference;
import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.RatingService;
import com.conferences.service.SpeechService;
import com.conferences.util.DataParser;
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
import java.util.Optional;


@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EditController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditController.class);
    private final ConferenceService conferenceService;
    private final SpeechService speechService;
    private final RatingService ratingService;


    @GetMapping(value = {"/editConferencePage"})
    public ModelAndView editConferencePage(@RequestParam("id") int id) {


        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("conference", conferenceService.findById(id).get());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferenceEdit");

        return modelAndView;
    }


    @RequestMapping(value = {"/edit"})
    public ModelAndView editConference(@RequestParam(value = "date") String date,
                                       @RequestParam(value = "name") String name, @RequestParam("conferenceId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");


        if (name.length() > 0 || date == null) {
            modelAndView = new ModelAndView();
            Conference conference = conferenceService.findById(id).get();
            conference.setDate(DataParser.toDate(date));
            conference.setName(name);

            conferenceService.edit(conference);
            user = (User) session.getAttribute("user");
            modelAndView.addObject("pageNum", 1);
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");
            modelAndView.addObject("conferences", conferenceService.findComingConferences(1));

            LOGGER.info(String.format("user [%s] edited conference with id %s", user.getUserId(), id));
        } else {
            modelAndView.addObject("conference", conferenceService.findById(id).get());

            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferenceEdit");
            LOGGER.info(String.format("user [%s] was not successfully edited conference with id %s", user.getUserId(), id));

        }
        return modelAndView;
    }

    @RequestMapping(value = {"/editSpeechPage"})
    public ModelAndView editSpeechPage(@RequestParam("speechId") String speechId) {
        ModelAndView modelAndView = new ModelAndView();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        int id = Integer.parseInt(speechId);
        Speech speech = speechService.findById(id).get();

        modelAndView.addObject("speech", speech);


        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speechEdit");
        return modelAndView;

    }

    @RequestMapping(value = "/editSpeech")
    public ModelAndView editSpeech(@RequestParam(value = "topic", required = false) Optional<String> topic,
                                   @RequestParam(value = "startHour", required = false) Optional<String> startHour,
                                   @RequestParam(value = "endHour", required = false) Optional<String> endHour,
                                   @RequestParam("id") String id,
                                   @RequestParam("suggestedTopic") String suggestedTopic) {

        ModelAndView modelAndView = new ModelAndView();


        Speech speech = speechService.findById(Integer.parseInt(id)).get();
        speech.setSuggestedTopic(suggestedTopic);
        topic.ifPresent(speech::setTopic);
        startHour.ifPresent(x -> speech.setStartHour(Integer.parseInt(startHour.get())));
        endHour.ifPresent(x -> speech.setEndHour(Integer.parseInt(endHour.get())));

        speech.setSuggestedTopic(suggestedTopic);


        speechService.save(speech);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("speeches", speechService.findAllByConference(speech.getConference().getConferenceId()));
        modelAndView.addObject("userSpeeches", speechService.findAllByUserId(user.getUserId()));

        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/speeches");
        return modelAndView;


    }

    @RequestMapping(value = {"/ratingPage"})
    public ModelAndView ratingPage() {
        ModelAndView modelAndView = new ModelAndView();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");

        modelAndView.addObject("rating",ratingService.findAll());
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");

        return modelAndView;
    }

    @RequestMapping(value = {"/editRating"})
    public ModelAndView editRating(@RequestParam("ratingId") String ratingId,@RequestParam("ratingMark") String ratingMark){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rating", ratingService.findAll());
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");

        ratingService.changeSpeakerRating(ratingId,ratingMark);

          modelAndView.setViewName(user.getRole().name().toLowerCase() + "/rating");


        return modelAndView;

    }
}
