package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;
import com.jho.halterocmsjpa.entity.Athlete;
import com.jho.halterocmsjpa.enums.GenderType;
import com.jho.halterocmsjpa.repository.AthleteRepository;
import com.jho.halterocmsjpa.service.AthleteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AthleteServiceImpl implements AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ModelMapper modelMaper;

    /**
     * Get all athletes.
     *
     * @param gender gender to be filtered.
     * @return a list with all the athletes.
     */
    @Override
    public List<AthleteDto> getAthletes(Optional<String> gender) {
        List<Athlete> athletes = new ArrayList<>();
        if (gender.isPresent()) {
            GenderType requestedGender = GenderType.fromValue(Integer.valueOf(gender.get()));
            athletes = athleteRepository.findAllByGender(requestedGender.getValue());
        } else {
            athletes = athleteRepository.findAll();
        }
        return modelMaper.map(athletes, new TypeToken<List<AthleteDto>>() {
        }.getType());
    }
}
