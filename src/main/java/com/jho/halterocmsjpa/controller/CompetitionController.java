package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.athlete.AthleteCreateDto;
import com.jho.halterocmsjpa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Competition controller layer.
 */
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * Creates a new competition.
     *
     * @param athleteCreateDto dto containing the information in order to create a competition.
     */
    @PostMapping("/")
    public void createCompetition(@Valid @RequestBody AthleteCreateDto athleteCreateDto) {
        competitionService.createCompetition(athleteCreateDto);
    }

}
