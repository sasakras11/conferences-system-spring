package com.conferences.controller;

import com.conferences.entity.ConferenceGroup;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class ConferencesController {

    private final ConferenceService conferenceService;


    @GetMapping(value = "/pageOfComing")
    public ModelAndView toPage(@RequestParam("template") String template, @RequestParam("page") String page) {

        ModelAndView modelAndView = new ModelAndView();
        int validatedPageNumber = page.chars().allMatch(Character::isDigit) && page.length() > 0 ? Integer.parseInt(page) : 1;
        modelAndView.addObject("pageNumber", conferenceService.getSameOrValidPage(validatedPageNumber, ConferenceGroup.COMING));
        modelAndView.addObject("conferences", conferenceService.findComingConferences(validatedPageNumber));
        modelAndView.setViewName(template);

        return modelAndView;
    }
    @GetMapping(value = "/pageOfFinished")
    public ModelAndView toFinishedConferencesPage(@RequestParam("template") String template,@RequestParam("page") String  page){

        ModelAndView modelAndView = new ModelAndView();
        int validatedPageNumber = page.chars().allMatch(Character::isDigit) && page.length() > 0 ? Integer.parseInt(page) : 1;
        modelAndView.addObject("pageNumber", conferenceService.getSameOrValidPage(validatedPageNumber, ConferenceGroup.FINISHED));
        modelAndView.addObject("conferences", conferenceService.findFinishedConferences(validatedPageNumber));
        modelAndView.setViewName(template);

        return modelAndView;
   }
   @GetMapping(value = "/finished")
        public ModelAndView toFinishedConferences(){

        ModelAndView modelAndView = new ModelAndView();

       ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
       HttpSession session = attr.getRequest().getSession();
          User user = (User) session.getAttribute("user");
       modelAndView.addObject("conferences",conferenceService.findFinishedConferences(1));
       modelAndView.addObject("pageNum",1);

       modelAndView.setViewName(user.getRole().name().toLowerCase()+"/finishedConferences");

 return modelAndView;
   }
    @GetMapping(value = "/coming")
    public ModelAndView toComingConferences() {

        ModelAndView modelAndView = new ModelAndView();

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("conferences", conferenceService.findComingConferences(1));
        modelAndView.addObject("pageNum", 1);
        session.setAttribute("conferencesType", "/pageOfComing");

        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;

    }


}
