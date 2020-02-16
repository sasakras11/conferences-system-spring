package com.conferences.service.impl;

import com.conferences.entity.Rating;
import com.conferences.repository.RatingRepository;
import com.conferences.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    public void save(List<Rating> rating) {

        ratingRepository.saveAll(rating);
    }
}
