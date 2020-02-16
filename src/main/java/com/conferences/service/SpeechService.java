package com.conferences.service;

import com.conferences.entity.Speech;
import com.conferences.entity.User;

import java.util.List;
import java.util.Optional;

public interface SpeechService {

    Optional<Speech> findById(int id);

    void save(Speech speech);

    List<Speech> findAllByConference(int id);

    List<Speech> findAllByUserId(Integer userId);

}
