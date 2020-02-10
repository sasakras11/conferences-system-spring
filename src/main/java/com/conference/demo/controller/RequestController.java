package com.conference.demo.controller;


import com.conference.demo.repository.ConferenceRepository;
import com.conference.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;


@Controller
public class RequestController {

    private UserService service;


    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }


    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("conferences", service.getComingConferences(new Date()));
        return "greeting";
    }
}
