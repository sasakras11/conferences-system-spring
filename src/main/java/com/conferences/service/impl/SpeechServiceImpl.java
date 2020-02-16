package com.conferences.service.impl;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.repository.SpeechRepository;
import com.conferences.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SpeechServiceImpl implements SpeechService {


    private final SpeechRepository speechRepository;

    @Override
    public Optional<Speech> findById(int id) {
        return speechRepository.findById(id);
    }

    @Override
    public void save(Speech speech) {
        speechRepository.save(speech);
    }

    @Override
    public List<Speech> findAllByConference(int id) {

        return speechRepository.findAllByConference_ConferenceId(id);
    }

    @Override
    public List<Speech> findAllByUserId(Integer userId) {
        return speechRepository.findAllByVisitors_UserId(userId);
    }


}
