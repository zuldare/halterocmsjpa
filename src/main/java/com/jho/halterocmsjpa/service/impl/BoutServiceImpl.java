package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;
import com.jho.halterocmsjpa.dto.bout.BoutDto;
import com.jho.halterocmsjpa.entity.Bout;
import com.jho.halterocmsjpa.entity.Competition;
import com.jho.halterocmsjpa.repository.BoutRepository;
import com.jho.halterocmsjpa.repository.CompetitionRepository;
import com.jho.halterocmsjpa.service.BoutService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Bout service implementation.
 */
@Service
@Slf4j
public class BoutServiceImpl implements BoutService {

    @Autowired
    private BoutRepository boutRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all bouts.
     *
     * @return a list of all bouts.
     */
    @Override
    public List<BoutDto> getBouts() {
        log.debug("Get all bouts");
        List<Bout> bouts = boutRepository.findAll();
        return modelMapper.map(bouts, new TypeToken<List<AthleteDto>>() {
        }.getType());
    }

    /**
     * Get all bouts of a competition.
     *
     * @param competitionId identification of a competition.
     * @return all the bouts of a competition.
     */
    @Override
    public List<BoutDto> getBoutsOfCompetition(Integer competitionId) {
        log.debug("Get all bouts of competition {}", competitionId);
        Competition competition = competitionRepository.findCompetitionById(competitionId);

        if (isNull(competition)) {
            return new ArrayList<>();
        }

        List<Bout> bouts = boutRepository.findBoutsByCompetition(competition);
        return modelMapper.map(bouts, new TypeToken<List<AthleteDto>>() {
        }.getType());
    }
}
