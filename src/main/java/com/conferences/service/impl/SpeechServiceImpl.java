package com.conferences.service.impl;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.exception.ValidationException;
import com.conferences.repository.SpeechRepository;
import com.conferences.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SpeechServiceImpl extends AbstractService<Speech, SpeechRepository> implements SpeechService {


    private static final String VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED = "conferences";
    private final SpeechRepository speechRepository;

    @Override
    public Speech findById(String id) {
        return findByIdIfPresentOrRedirect(id, speechRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
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


    public Speech editSpeechAndGet(String topic,
                                   String startHour,
                                   String endHour,
                                   String suggestedTopic, String id) {


        Speech speech = findByIdIfPresentOrRedirect(id, speechRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);

        Optional.ofNullable(suggestedTopic).ifPresent(x->{
            if (VALID_TOPIC.test(x)) {
                speech.setSuggestedTopic(x);
            } else {
                throw new ValidationException(VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
            }
        });
        Optional.ofNullable(topic).ifPresent(x -> {
            if (VALID_TOPIC.test(x)) {
                speech.setTopic(x);
            } else {
                throw new ValidationException(VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
            }
        });
        Optional.ofNullable(startHour).ifPresent(x -> {
            if (VALID_HOUR.test(Integer.parseInt(x))) {
                speech.setStartHour(Integer.parseInt(x));
            } else {
                throw new ValidationException(VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
            }
        });
        Optional.ofNullable(endHour).ifPresent(x -> {
            if (VALID_HOUR.test(Integer.parseInt(x))) {
                speech.setEndHour(Integer.parseInt(x));
            } else {
                throw new ValidationException(VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
            }
        });

        speechRepository.save(speech);
        return speech;

    }

    public Speech reservePlaceAndGet(String speechId ,User user){

        Speech speech = findByIdIfPresentOrRedirect(speechId,speechRepository,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
        List<User> visitors  = speech.getVisitors();
        visitors.add(user);
        speechRepository.save(speech);
        return speech;
    }

}
