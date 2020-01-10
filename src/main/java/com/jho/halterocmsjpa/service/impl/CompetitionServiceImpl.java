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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (isNull(competition)) {
            log.error("Competition {} could not be deleted because it does not exists.", competitionId);
            throw new CompetitionNotExists();
        }

        competitionRepository.deleteById(competitionId);
        log.info("Deleted competition {}", competitionId);
    }


    private void checkIfCompetitionExists(CompetitionCreateDto competitionCreateDto) {
        Competition competition = competitionRepository.findCompetitionByDescriptionAndBeginDate(competitionCreateDto.getDescription(), competitionCreateDto.getBeginDate());
        if (competition != null) {
            log.error("A competition with name {} and begin date {} already exists.", competitionCreateDto.getDescription(), competitionCreateDto.getBeginDate());
            throw new CompetitionAlreadyExists();
        }
    }

}
