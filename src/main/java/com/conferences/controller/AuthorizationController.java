package com.conferences.controller;

import com.conferences.entity.Role;
import com.conferences.entity.User;
import com.conferences.exception.ValidationException;
import com.conferences.service.ConferenceService;
import com.conferences.service.SpeechService;
import com.conferences.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static java.lang.String.format;


@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);
    private final UserService userService;
    private final ConferenceService conferenceService;


    @GetMapping(value = {"/"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("start");


        return modelAndView;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView getBackPage() {
        ModelAndView modelAndView = new ModelAndView();
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();

        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            modelAndView.addObject("conferences", conferenceService.findComingConferences(1));
            modelAndView.addObject("pageNum", 1);
            System.out.println(user.getRole().name().toLowerCase() + "/conferences");
            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");
            return modelAndView;
        }

        modelAndView.setViewName("start");
        return modelAndView;

    }
    @GetMapping(value = {"/conferences"})
    public ModelAndView conferencePage(){
        ModelAndView modelAndView = new ModelAndView();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        modelAndView.addObject("conferences", conferenceService.findComingConferences(1));
        modelAndView.addObject("pageNumber", 1);
        User user = (User) request.getSession().getAttribute("user");

        modelAndView.setViewName(user.getRole().name().toLowerCase()+"/conferences");
        return modelAndView;

    }

    @PostMapping(value = {"/conferences"})
    public ModelAndView authorization(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            RequestAttributes requestAttributes = RequestContextHolder
                    .currentRequestAttributes();

            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            request.getSession().setAttribute("user", user.get());
            modelAndView.addObject("conferences", conferenceService.findComingConferences(1));
            modelAndView.addObject("pageNum", 1);

            modelAndView.setViewName(user.get().getRole().name().toLowerCase() + "/conferences");

            LOGGER.info(format("user with username [%s] and password [%s]} logged in ", username, password));

        } else {
            modelAndView.addObject("error", "wrong credentials");
            modelAndView.setViewName("start");
            LOGGER.info(format("user with username [%s] and password [%s] failed in logging in  ", username, password));
        }
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = {"/register"})
    public ModelAndView registration(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.register(username, password);

            modelAndView.addObject("conferences", conferenceService.findComingConferences(1));
            modelAndView.addObject("pageNumber", 1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            session.setAttribute("user", user);

            modelAndView.setViewName(user.getRole().name().toLowerCase() + "/conferences");

            LOGGER.info(format("user with username [%s] and password [%s] registered successfully", username, password));

            return modelAndView;
        } catch (ValidationException e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
}
