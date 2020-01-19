package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Bout;
import com.jho.halterocmsjpa.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Bout repository layer.
 */
@Repository
public interface BoutRepository extends JpaRepository<Bout, Integer> {

    List<Bout> findBoutsByCompetition(Competition competition);
}
