package com.conference.demo.repository;

import com.conference.demo.entity.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeechRepository extends JpaRepository<Speech,Integer> {

    @Override
    <S extends Speech> S save(S s);

    @Override
    Optional<Speech> findById(Integer integer);
}
