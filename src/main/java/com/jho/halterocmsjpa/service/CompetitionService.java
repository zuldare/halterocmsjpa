package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;

import java.util.List;

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

    /**
     * Gets all the competitions.
     *
     * @return a list with all the competitions.
     */
    List<CompetitionDto> getCompetitions();

    /**
     * Get a competition according to a competition identification.
     *
     * @param competitionId identification of the competition.
     * @return the competition with the demanded identification.
     */
    CompetitionDto getCompetition(Integer competitionId);
}
