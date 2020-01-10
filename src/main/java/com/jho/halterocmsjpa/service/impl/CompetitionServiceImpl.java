package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;
import com.jho.halterocmsjpa.entity.Competition;
import com.jho.halterocmsjpa.exception.CompetitionAlreadyExists;
import com.jho.halterocmsjpa.exception.CompetitionNotExists;
import com.jho.halterocmsjpa.repository.CompetitionRepository;
import com.jho.halterocmsjpa.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Competition service layer.
 */
@Service
@Slf4j
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new competition.
     *
     * @param competitionCreateDto dto containing the information in order to create a competition
     * @return a dto containing the newly created competition.
     */
    @Override
    public CompetitionDto createCompetition(CompetitionCreateDto competitionCreateDto) {
        checkIfCompetitionExists(competitionCreateDto);
        Competition competition = competitionRepository.save(modelMapper.map(competitionCreateDto, Competition.class));
        return modelMapper.map(competition, CompetitionDto.class);
    }

    /**
     * Delete competition according to an identification.
     *
     * @param competitionId identification of the competition to delete.
     */
    @Override
    public void deleteCompetition(Integer competitionId) {
        Competition competition = competitionRepository.findCompetitionById(competitionId);

        checkIfCompetitionExists(competitionId, competition);

        competitionRepository.deleteById(competitionId);
        log.info("Deleted competition {}", competitionId);
    }

    /**
     * Gets all the competitions.
     *
     * @return a list with all the competitions.
     */
    @Override
    public List<CompetitionDto> getCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        return modelMapper.map(competitions, new TypeToken<List<CompetitionDto>>() {
        }.getType());
    }

    /**
     * Get a competition according to a competition identification.
     *
     * @param competitionId identification of the competition.
     * @return the competition with the demanded identification.
     */
    @Override
    public CompetitionDto getCompetition(Integer competitionId) {
        Competition competition = competitionRepository.findCompetitionById(competitionId);
        checkIfCompetitionExists(competitionId, competition);

        return modelMapper.map(competition, CompetitionDto.class);
    }

    private void checkIfCompetitionExists(CompetitionCreateDto competitionCreateDto) {
        Competition competition = competitionRepository.findCompetitionByDescriptionAndBeginDate(competitionCreateDto.getDescription(), competitionCreateDto.getBeginDate());
        if (competition != null) {
            log.error("A competition with name {} and begin date {} already exists.", competitionCreateDto.getDescription(), competitionCreateDto.getBeginDate());
            throw new CompetitionAlreadyExists();
        }
    }

    private void checkIfCompetitionExists(Integer competitionId, Competition competition) {
        if (isNull(competition)) {
            log.error("Competition {} could not be deleted because it does not exists.", competitionId);
            throw new CompetitionNotExists();
        }
    }

}
