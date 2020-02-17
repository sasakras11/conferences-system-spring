package com.conferences.service;

import com.conferences.entity.Conference;
import com.conferences.entity.ConferenceGroup;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {

    List<Conference> findComingConferences(String page);

    List<Conference> findFinishedConferences(String page);


    int getSameOrValidPage(String page, ConferenceGroup conferenceGroup);

    Conference findById(String id);

    void editConference(String name,String date,String conferenceId);

}
