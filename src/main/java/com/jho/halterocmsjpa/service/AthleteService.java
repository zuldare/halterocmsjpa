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
}
