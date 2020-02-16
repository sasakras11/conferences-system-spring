package com.conferences.service;

import com.conferences.entity.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();
    void save(Rating speakerRating);

    void changeSpeakerRating(String ratingId,String ratingMark);


}
