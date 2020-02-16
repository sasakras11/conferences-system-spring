package com.conferences.repository;

import com.conferences.entity.Conference;
import com.conferences.entity.Speech;
import com.conferences.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from speeches_visitors where visitors_user_id = ?1 and speeches_speech_id = ?2",nativeQuery = true)
      void deleteReservation(int userId, int speechId);





}
