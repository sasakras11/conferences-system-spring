package com.conferences.controller;

import com.conferences.entity.ConferenceGroup;
import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class ConferencesController extends AbstractController{

    private final ConferenceService conferenceService;
     private final UserBean userBean;

    @GetMapping(value = "/pageOfComing")
    public ModelAndView toPage(@RequestParam("template") String template, @RequestParam("page") String page) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageNumber", conferenceService.getSameOrValidPage(page, ConferenceGroup.COMING));
        modelAndView.addObject("conferences", conferenceService.findComingConferences(page));
        modelAndView.setViewName(template);

        return modelAndView;
    }
    @GetMapping(value = "/pageOfFinished")
    public ModelAndView toFinishedConferencesPage(@RequestParam("template") String template,@RequestParam("page") String  page){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageNumber", conferenceService.getSameOrValidPage(page, ConferenceGroup.FINISHED));
        modelAndView.addObject("conferences", conferenceService.findFinishedConferences(page));
        modelAndView.setViewName(template);

        return modelAndView;
   }
   @GetMapping(value = "/finished")
        public ModelAndView toFinishedConferences(){

        ModelAndView modelAndView = new ModelAndView();
       User user = userBean.getUser();
       modelAndView.addObject("conferences",conferenceService.findFinishedConferences("1"));
       modelAndView.addObject("pageNum",1);

       modelAndView.setViewName(user.getRole().name().toLowerCase()+"/finishedConferences");

 return modelAndView;
   }
    @GetMapping(value = "/coming")
    public ModelAndView toComingConferences() {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.addObject("pageNum", 1);
           setSessionAttribute("conferencesType", "/pageOfComing");
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;

    }


}
