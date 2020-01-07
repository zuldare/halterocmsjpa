package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Competition repository layer.
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    Competition findCompetitionByDescriptionAndBeginDate(String description, Long beginDate);

}
