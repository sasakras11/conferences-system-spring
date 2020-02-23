package com.conferences.service.impl;

import com.conferences.entity.Conference;
import com.conferences.entity.ConferenceGroup;
import com.conferences.repository.ConferenceRepository;
import com.conferences.service.ConferenceService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConferenceServiceImpl extends AbstractService<Conference, ConferenceRepository> implements ConferenceService {

    private static final int ITEMS_PER_PAGE = 4;
    private static final String VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED = "conferences";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceServiceImpl.class);
    private final ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> findComingConferences(String page) {
        return conferenceRepository.findAllByDateGreaterThan(new Date(),
                PageRequest.of(getSameOrValidPage(page, ConferenceGroup.COMING) - 1, ITEMS_PER_PAGE));
    }

    @Override
    public List<Conference> findFinishedConferences(String page) {

        return conferenceRepository.findAllByDateLessThan(new Date(),
                PageRequest.of(getSameOrValidPage(page, ConferenceGroup.FINISHED) - 1, ITEMS_PER_PAGE));
    }

    public int getSameOrValidPage(String page, ConferenceGroup conferenceGroup) {
        int intPage = getParsedOctalNumberOrRedirect(page, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);

        Date date = new Date();
        int count = conferenceGroup == ConferenceGroup.COMING ?
                conferenceRepository.countByDateGreaterThan(date)
                : conferenceRepository.countByDateLessThan(date);

        int maxPage = count / ITEMS_PER_PAGE;
        if (count % ITEMS_PER_PAGE != 0) {
            maxPage++;
        }
        if (intPage < 1) return 1;
        if (intPage > maxPage) return maxPage;

        return intPage;
    }

    @Override
    public Conference findById(String id) {
        return findByIdIfPresentOrRedirect(id, conferenceRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
    }

    @Override
    public void editConference(String name, String date, String conferenceId) {
        Conference conference = findByIdIfPresentOrRedirect(conferenceId, conferenceRepository, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED);
        conference.setDate(getParsedDateOrRedirect(date, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED));
        conference.setName(getValidatedNameOrRedirect(name, VIEW_TO_RETURN_IF_EXCEPTION_HAPPENED));
        conferenceRepository.save(conference);
        LOGGER.info(String.format("updated conference with id [%s] - new name - [%s], date - [%s]", conferenceId, name, date));
    }
}


