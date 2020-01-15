package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;
import com.jho.halterocmsjpa.entity.Athlete;
import com.jho.halterocmsjpa.enums.GenderType;
import com.jho.halterocmsjpa.exception.AthleteNotFoundException;
import com.jho.halterocmsjpa.exception.GenderNotExistsException;
import com.jho.halterocmsjpa.repository.AthleteRepository;
import com.jho.halterocmsjpa.service.AthleteService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * Athlete service layer.
 */
@Service
@Slf4j
public class AthleteServiceImpl implements AthleteService {

    private static final String MALE_NAME = "MALE";
    private static final String FEMALE_NAME = "FEMALE";
    private static final List<String> GENDERS = Arrays.asList(MALE_NAME, FEMALE_NAME);

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private ModelMapper modelMapper;


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
            log.info("Find all athletes with gender {}", gender.get());
            athletes = getAthletesAccordingToGender(gender.get());
        } else {
            log.info("Find all athletes");
            athletes = athleteRepository.findAll();
        }
        return modelMapper.map(athletes, new TypeToken<List<AthleteDto>>() {
        }.getType());
    }

    /**
     * Get an athlete according an identification.
     *
     * @param athleteId identification of the athlete.
     * @return the athlete with the matching identification.
     */
    @Override
    public AthleteDto getAthlete(Integer athleteId) {
        Athlete athlete = athleteRepository.findAthleteById(athleteId);
        if (isNull(athlete)) {
            log.error("The athlete {} does not exists", athleteId);
            throw new AthleteNotFoundException();
        }
        return modelMapper.map(athlete, AthleteDto.class);
    }

    /**
     * Returns the athletes according to a gender.
     *
     * @param gender name of the gender to be obtained.
     * @return a list of athletes of a gender if determined, every gender otherwise.
     */
    private List<Athlete> getAthletesAccordingToGender(String gender) {
        gender = StringUtils.trimAllWhitespace(gender).toUpperCase();
        if (!GENDERS.contains(gender)) {
            log.error("Gender {} does not exists", gender);
            throw new GenderNotExistsException();
        }

        GenderType requestedGender = GenderType.valueOf(gender);
        return athleteRepository.findAllByGender(requestedGender.getValue());
    }

    /**
     * Delete athlete according to an identification.
     *
     * @param athleteId identification of the athlete to be deleted.
     */
    @Override
    public void deleteAthlete(Integer athleteId) {
        Athlete athlete = athleteRepository.findAthleteById(athleteId);
        if (isNull(athlete)) {
            log.error("Athlete {} does not exists", athleteId);
            throw new AthleteNotFoundException();
        }
        athleteRepository.deleteById(athlete.getId());
    }

}
