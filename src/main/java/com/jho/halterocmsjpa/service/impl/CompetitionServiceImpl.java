package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.athlete.AthleteCreateDto;
import com.jho.halterocmsjpa.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Competition service layer.
 */
@Service
@Slf4j
public class CompetitionServiceImpl implements CompetitionService {

    /**
     * Creates a new competition.
     *
     * @param athleteCreateDto dto containing the information in order to create a competition.
     */
    @Override
    public void createCompetition(AthleteCreateDto athleteCreateDto) {

    }
}
