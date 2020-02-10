package com.conference.demo.repository;

import com.conference.demo.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConferenceRepository  extends JpaRepository<Conference,Integer>{


    List<Conference> findByDateGreaterThan(Date date);
    List<Conference> findByDateLessThan(Date date);
}
