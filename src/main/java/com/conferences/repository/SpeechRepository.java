package com.conferences.repository;

import com.conferences.entity.Speech;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpeechRepository extends PagingAndSortingRepository<Speech, Integer> {

    @Query(value = "select *from speeches as s inner join conferences_speeches as cs on speeches_speech_id=s.speech_id where conference_conference_id = ?", nativeQuery = true)
    List<Speech> findAllByConference(int id);


}
