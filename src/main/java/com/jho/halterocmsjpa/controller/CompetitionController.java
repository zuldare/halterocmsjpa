package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;
import com.jho.halterocmsjpa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Competition controller layer.
 */
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * Gets all the competitions.
     *
     * @return a list with all the competitions.
     */
    @GetMapping("/competitions")
    public List<CompetitionDto> getCompetitions() {
        return competitionService.getCompetitions();
    }

    /**
     * Creates a new competition.
     *
     * @param competitionCreateDto dto containing the information in order to create a competition
     * @return a dto containing the newly created competition.
     */
    @PostMapping("/competitions")
    public CompetitionDto createCompetition(@Valid @RequestBody CompetitionCreateDto competitionCreateDto) {
        return competitionService.createCompetition(competitionCreateDto);
    }

    /**
     * Delete competition according to an identification.
     *
     * @param competitionId identification of the competition to delete.
     */
    @DeleteMapping("/competitions/{competitionId}")
    public void deleteCompetition(@PathVariable Integer competitionId) {
        competitionService.deleteCompetition(competitionId);
    }

}
