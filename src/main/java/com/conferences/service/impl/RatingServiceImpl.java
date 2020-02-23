package com.conferences.service.impl;

import com.conferences.entity.Rating;
import com.conferences.repository.RatingRepository;
import com.conferences.service.RatingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RatingServiceImpl extends AbstractService<Rating, RatingRepository> implements RatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);
    private static final String PAGE_IF_EXCEPTION_HAPPENED = "rating";
    private final RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void save(Rating speakerRating) {

        ratingRepository.save(speakerRating);
        LOGGER.info(String.format("saved rating with id [%o] , speaker [%s], and rating mark - [%o]",
                speakerRating.getId(), speakerRating.getUser(), speakerRating.getRating()));
    }

    @Override
    public void changeSpeakerRating(String ratingId, String ratingMark) {

        int mark = getParsedOctalNumberOrRedirect(ratingMark, PAGE_IF_EXCEPTION_HAPPENED);
        Rating rating = findByIdIfPresentOrRedirect(ratingId, ratingRepository, PAGE_IF_EXCEPTION_HAPPENED);
        rating.setRating(mark);
        ratingRepository.save(rating);
        LOGGER.info(String.format("changed rating with id [%s] .New rating mark value - [%o]", ratingId, mark));
    }
}
