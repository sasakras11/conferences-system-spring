package com.conference.demo.repository;

import com.conference.demo.entity.Conference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConferenceRepository  extends PagingAndSortingRepository<Conference,Integer> {


    List<Conference> findAllByDateGreaterThan(Date date, Pageable pageable);
    List<Conference> findAllByDateLessThan(Date date,Pageable pageable);


}
