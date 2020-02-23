package com.conferences.controller;

import com.conferences.entity.User;
import com.conferences.service.ConferenceService;
import com.conferences.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController  {

    private final UserService userService;
    private final ConferenceService conferenceService;
    private final UserBean userBean;

    @GetMapping(value = {"/"})
    public ModelAndView getLoginPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("start");

        return modelAndView;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView getConferencesPage() {

        ModelAndView modelAndView = new ModelAndView();
        User user = userBean.getUser();
        if (user != null) {
            modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
            modelAndView.addObject("pageNum", 1);
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");
            return modelAndView;
        }
        modelAndView.setViewName("start");

        return modelAndView;

    }

    @GetMapping(value = {"/conferences"})
    public ModelAndView getComingConferencesPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.addObject("pageNumber", 1);
        User user = userBean.getUser();
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;

    }

    @PostMapping(value = {"/conferences"})
    public ModelAndView loggingIn(@RequestParam("username") String username, @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userService.login(username, password);
         userBean.setUser(user);
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.addObject("pageNum", 1);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registerPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping(value = {"/register"})
    public ModelAndView registration(@RequestParam("username") String username, @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userService.register(username, password);
        userBean.setUser(user);
        modelAndView.addObject("conferences", conferenceService.findComingConferences("1"));
        modelAndView.addObject("pageNum", 1);
        modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

        return modelAndView;
    }
}
