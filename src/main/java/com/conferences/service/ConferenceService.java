package com.conferences.service;

import com.conferences.entity.Conference;
import com.conferences.entity.ConferenceGroup;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {

    List<Conference> findComingConferences(int page);

    List<Conference> findFinishedConferences(int page);

    void edit(Conference conference);

    int getSameOrValidPage(int page, ConferenceGroup conferenceGroup);

    Optional<Conference> findById(int id);

}
