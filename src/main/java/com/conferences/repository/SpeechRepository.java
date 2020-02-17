package com.conferences.repository;

import com.conferences.entity.Speech;
import com.conferences.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpeechRepository extends JpaRepository<Speech, Integer> {


    List<Speech> findAllByConference_ConferenceId(int id);
    List<Speech> findAllByVisitors_UserId(int id);



}
