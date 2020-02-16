package com.conferences.service.impl;

import com.conferences.entity.Conference;
import com.conferences.entity.ConferenceGroup;
import com.conferences.repository.ConferenceRepository;
import com.conferences.service.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConferenceServiceImpl implements ConferenceService {

    private static final int ITEMS_PER_PAGE = 4;

    private final ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> findComingConferences(int page) {
        return conferenceRepository.findAllByDateGreaterThan(new Date(),
                PageRequest.of(getSameOrValidPage(page, ConferenceGroup.COMING) - 1, ITEMS_PER_PAGE));
    }

    @Override
    public List<Conference> findFinishedConferences(int page) {
        return conferenceRepository.findAllByDateLessThan(new Date(),
                PageRequest.of(getSameOrValidPage(page, ConferenceGroup.FINISHED) - 1, ITEMS_PER_PAGE));
    }

    @Override
    public void edit(Conference conference) {
        conferenceRepository.save(conference);
    }


    public int getSameOrValidPage(int page, ConferenceGroup conferenceGroup) {
        Date date = new Date();
        int count = conferenceGroup == ConferenceGroup.COMING ?
                conferenceRepository.countByDateGreaterThan(date)
                : conferenceRepository.countByDateLessThan(date);

        int maxPage = count / ITEMS_PER_PAGE;
        if (count % ITEMS_PER_PAGE != 0) {
            maxPage++;
        }
        if (page < 1) return 1;
        if (page > maxPage) return maxPage;

        return page;
    }

    @Override
    public Optional<Conference> findById(int id) {
        return conferenceRepository.findById(id);
    }
}

