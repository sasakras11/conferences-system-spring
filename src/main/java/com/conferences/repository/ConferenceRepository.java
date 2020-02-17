package com.conferences.repository;

import com.conferences.entity.Conference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    List<Conference> findAllByDateGreaterThan(Date date, Pageable pageable);

    List<Conference> findAllByDateLessThan(Date date, Pageable pageable);

    int countByDateGreaterThan(Date date);

    int countByDateLessThan(Date date);


}
