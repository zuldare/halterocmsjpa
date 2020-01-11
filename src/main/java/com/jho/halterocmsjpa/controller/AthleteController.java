package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;
import com.jho.halterocmsjpa.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Athlete controller layer.
 */
@RestController
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    /**
     * Get all athletes.
     *
     * @param gender gender to be filtered.
     * @return a list with all the athletes.
     */
    @GetMapping("/athletes")
    public List<AthleteDto> getAthletes(@RequestParam(value = "gender", required = false) Optional<String> gender) {
        return athleteService.getAthletes(gender);
    }

    /**
     * Get an athlete according an identification.
     *
     * @param athleteId identifiation of the athlete.
     * @return the athlete with the matching identification.
     */
    @GetMapping("/athletes/{athleteId}")
    public AthleteDto getAthlete(@PathVariable Integer athleteId) {
        return athleteService.getAthlete(athleteId);
    }
}
