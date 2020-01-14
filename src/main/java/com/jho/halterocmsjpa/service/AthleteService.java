package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;

import java.util.List;
import java.util.Optional;

public interface AthleteService {

    /**
     * Get all athletes.
     *
     * @param gender gender to be filtered.
     * @return a list with all the athletes.
     */
    List<AthleteDto> getAthletes(Optional<String> gender);

    /**
     * Get an athlete according an identification.
     *
     * @param athleteId identifiation of the athlete.
     * @return the athlete with the matching identification.
     */
    AthleteDto getAthlete(Integer athleteId);

    /**
     * Delete athlete according to an identification.
     *
     * @param athleteId identification of the athlete to be deleted.
     */
    void deleteAthlete(Integer athleteId);
}
