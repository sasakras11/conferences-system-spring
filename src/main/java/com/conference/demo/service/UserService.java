package com.conference.demo.service;


import com.conference.demo.entity.Conference;
import com.conference.demo.entity.Role;
import com.conference.demo.entity.User;
import com.conference.demo.repository.ConferenceRepository;
import com.conference.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;


    @Autowired
    public UserService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

public List<Conference> getComingConferences(Date date){
        return conferenceRepository.findByDateGreaterThan(date);
}

    public User findByUsername(String username){
        return userRepository.findUserByUsername("alex");
    }
}
