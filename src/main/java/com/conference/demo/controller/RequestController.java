package com.conference.demo.controller;

import com.conference.demo.Exception.ValidationException;
import com.conference.demo.Util.Jsp.JspMap;
import com.conference.demo.Util.Jsp.Stage;
import com.conference.demo.entity.Conference;
import com.conference.demo.entity.ConferenceGroup;
import com.conference.demo.entity.User;
import com.conference.demo.service.ConferenceService;
import com.conference.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

@Controller
public class RequestController {

    private UserServiceImpl service;
    private ConferenceService conferenceService;

    @Autowired
    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Autowired
    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String start(Model model){
        return "start";
    }





    @PostMapping(value = {"/authorization"})
    public String register(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
           User user = service.register(username, password);

            ServletRequestAttributes attr = (ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes();
           HttpSession session = attr.getRequest().getSession();

           session.setAttribute("user",user);
           model.addAttribute("conferences",conferenceService.findComingConferences(1));
            model.addAttribute("pageNumber",1);


            return JspMap.getJspUrl(user.getRole(), Stage.CONFERENCES_COMING);
        } catch (ValidationException e) {
           model.addAttribute("error",e.getMessage());
           return "start";
        }
    }

    @GetMapping(value = "/toPage")
    public String toPage(Model model,@RequestParam("template") String template,@RequestParam("page") int page){

         model.addAttribute("conferences", conferenceService.findComingConferences(page));
         model.addAttribute("pageNumber",conferenceService.getSameOrValidPage(page, ConferenceGroup.COMING));

         return template;
    }



}
