package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;

public interface CompetitionService {

    /**
     * Creates a new competition.
     *
     * @param competitionCreateDto dto containing the information in order to create a competition
     * @return a dto containing the newly created competition.
     */
    CompetitionDto createCompetition(CompetitionCreateDto competitionCreateDto);

    /**
     * Delete competition according to an identification.
     *
     * @param competitionId identification of the competition to delete.
     */
    void deleteCompetition(Integer competitionId);
}
