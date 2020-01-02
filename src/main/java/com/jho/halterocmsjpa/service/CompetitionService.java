package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.athlete.AthleteCreateDto;

public interface CompetitionService {

    /**
     * Creates a new competition.
     *
     * @param athleteCreateDto dto containing the information in order to create a competition.
     */
    void createCompetition(AthleteCreateDto athleteCreateDto);
}
