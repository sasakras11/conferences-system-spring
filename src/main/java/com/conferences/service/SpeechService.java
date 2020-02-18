package com.conferences.service;

import com.conferences.entity.Speech;
import com.conferences.entity.User;

import java.util.List;
import java.util.Optional;

public interface SpeechService {

    Speech findById(String id);

    void save(Speech speech);

    List<Speech> findAllByConferenceId(String id);

    List<Speech> findAllByUserId(Integer userId);

    public Speech reservePlaceAndGet(String speechId ,User user);

    public Speech editSpeechAndGet(String topic, String startHour, String endHour, String suggestedTopic, String id);
}
