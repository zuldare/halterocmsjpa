package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.bout.BoutDto;
import com.jho.halterocmsjpa.service.BoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Bout controller layer.
 */
@RestController
public class BoutController {

    @Autowired
    private BoutService boutService;

    /**
     * Get all bouts.
     *
     * @return a list of all bouts.
     */
    @GetMapping("/bouts")
    public List<BoutDto> getBouts() {
        return boutService.getBouts();
    }

    /**
     * Get all bouts of a competition.
     *
     * @param competitionId identification of a competition.
     * @return all the bouts of a competition.
     */
    @GetMapping("/bouts/{competitionId}")
    public List<BoutDto> getBoutsOfCompetition(@PathVariable("athleteId") Integer competitionId) {
        return boutService.getBoutsOfCompetition(competitionId);
    }

}
