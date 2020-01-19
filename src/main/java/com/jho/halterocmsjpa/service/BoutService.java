package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.bout.BoutDto;

import java.util.List;

public interface BoutService {

    /**
     * Get all bouts.
     *
     * @return a list of all bouts.
     */
    List<BoutDto> getBouts();

    /**
     * Get all bouts of a competition.
     *
     * @param competitionId identification of a competition.
     * @return all the bouts of a competition.
     */
    List<BoutDto> getBoutsOfCompetition(Integer competitionId);
}
