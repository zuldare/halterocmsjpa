package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Integer> {

}
