package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Athlete repository layer.
 */
@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Integer> {

    List<Athlete> findAllByGender(Integer gender);

    Athlete findAthleteById(Integer athleteId);
}
