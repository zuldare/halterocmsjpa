package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;
import com.jho.halterocmsjpa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param competitionCreateDto dto containing the information in order to create a competition
     * @return a dto containing the newly created competition.
     */
    @PostMapping("/")
    public CompetitionDto createCompetition(@Valid @RequestBody CompetitionCreateDto competitionCreateDto) {
        return competitionService.createCompetition(competitionCreateDto);
    }

    /**
     * Delete competition according to an identification.
     *
     * @param competitionId identification of the competition to delete.
     */
    @PostMapping("/{competitionId}")
    public void deleteCompetition(@PathVariable Integer competitionId) {
        competitionService.deleteCompetition(competitionId);
    }

}
