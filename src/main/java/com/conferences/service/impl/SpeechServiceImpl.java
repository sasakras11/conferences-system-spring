package com.conferences.service.impl;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import com.conferences.repository.SpeechRepository;
import com.conferences.service.SpeechService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SpeechServiceImpl extends AbstractService<Speech, SpeechRepository> implements SpeechService {

    private static final String VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED = "conferences";
    private final SpeechRepository speechRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechServiceImpl.class);

    @Override
    public Speech findById(String id) {
        return findByIdIfPresentOrRedirect(id, speechRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
    }

    @Override
    public void save(Speech speech) {
        speechRepository.save(speech);
    }

    @Override
    public List<Speech> findAllByConferenceId(String  id) {
    int validatedId = getParsedOctalNumberOrRedirect(id,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
        return speechRepository.findAllByConference_ConferenceId(validatedId);
    }

    @Override
    public List<Speech> findAllByUserId(Integer userId) {
        return speechRepository.findAllByVisitors_UserId(userId);
    }

    public Speech editSpeechAndGet(String topic,
                                   String startHour,
                                   String endHour,
                                   String suggestedTopic,
                                   String id) {

        Speech speech = findByIdIfPresentOrRedirect(id, speechRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);

            Optional.ofNullable(suggestedTopic).ifPresent(x ->
                    speech.setSuggestedTopic(getValidatedNameOrRedirect(suggestedTopic, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED)));
        Optional.ofNullable(topic).ifPresent(x ->
                speech.setTopic(getValidatedNameOrRedirect(topic,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED)));
        Optional.ofNullable(startHour).ifPresent(x ->
                speech.setStartHour(getValidHourOrRedirect(startHour,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED)));
        Optional.ofNullable(endHour).ifPresent(x ->
                speech.setEndHour(getValidEndHourOrRedirect(startHour,endHour,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED)));

        speechRepository.save(speech);
        LOGGER.info(String.format("edited speech with id - [%s].New values topic - [%s], startHour - [%s], endHour - [%s] ,suggestedTopic - [%s]",id,topic,startHour,endHour,suggestedTopic));

        return speech;
    }

    public Speech reservePlaceAndGet(String speechId ,User user){

        Speech speech = findByIdIfPresentOrRedirect(speechId,speechRepository,VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
        List<User> visitors  = speech.getVisitors();
        visitors.add(user);
        speechRepository.save(speech);
        LOGGER.info(String.format("user with username [%s] reserve place on speech with topic [%s]",user.getUsername(),speech.getTopic()));

        return speech;
    }
}
