package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;
import com.jho.halterocmsjpa.entity.Competition;
import com.jho.halterocmsjpa.exception.CompetitionAlreadyExistsException;
import com.jho.halterocmsjpa.exception.CompetitionNotFoundException;
import com.jho.halterocmsjpa.repository.CompetitionRepository;
import com.jho.halterocmsjpa.service.impl.CompetitionServiceImpl;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CompetitionServiceTest {

    private static final String COMPETITION_DESC_1 = "Diktator Cup";
    private static final String COMPETITION_DESC_2 = "Cup 2";
    private static final String ORGANIZER_1 = "Tracius";
    private static final String ORGANIZER_2 = "Sideropolis";
    private static final String PLACE_1 = "Calle Industrias 32, Alcorcón";
    private static final String PLACE_2 = "Siderpolis Club";
    private static final Long DAY_0 = 0L;   //1970-01-01
    private static final Long DAY_1 = 86400L; //1970-01-02
    private static final Integer ID_1 = 1;
    private static final Integer ID_2 = 2;

    @MockBean
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionService competitionService;

    @Test(expected = CompetitionAlreadyExistsException.class)
    public void createCompetitionAlreadyExistsShouldThrowException() {
        DateTimeUtils.setCurrentMillisFixed(0L);

        //Given
        CompetitionCreateDto competitionCreateDto = CompetitionCreateDto.builder()
                .beginDate(DAY_0)
                .endDate(DAY_1)
                .description(COMPETITION_DESC_1)
                .organizer(ORGANIZER_1)
                .place(PLACE_1)
                .build();

        //When
        when(competitionRepository.findCompetitionByDescriptionAndBeginDate(anyString(), anyLong()))
                .thenReturn(Competition.builder()
                        .description(COMPETITION_DESC_1)
                        .beginDate(new Date(DAY_0))
                        .endDate(new Date(DAY_1))
                        .build());
        //Assert
        competitionService.createCompetition(competitionCreateDto);
    }

    @Test
    public void createCompetitionSameDescriptionDifferentBeginDateShouldReturnOK() {
        DateTimeUtils.setCurrentMillisFixed(0L);

        //Given
        CompetitionCreateDto competitionCreateDto = CompetitionCreateDto.builder()
                .beginDate(DAY_0)
                .endDate(DAY_1)
                .description(COMPETITION_DESC_1)
                .organizer(ORGANIZER_1)
                .place(PLACE_1)
                .build();

        //When
        when(competitionRepository.save(any(Competition.class)))
                .thenReturn(Competition.builder()
                        .description(COMPETITION_DESC_1)
                        .beginDate(new Date(DAY_0))
                        .endDate(new Date(DAY_1))
                        .organizer(ORGANIZER_1)
                        .place(PLACE_1)
                        .id(ID_1)
                        .build());

        // Assert
        CompetitionDto competitionDto = competitionService.createCompetition(competitionCreateDto);

        assertNotNull(competitionDto);
        assertNotNull(competitionDto.getId());
        assertThat(competitionDto.getBeginDate(), is(new Date(DAY_0)));
        assertThat(competitionDto.getEndDate(), is(new Date(DAY_1)));
        assertThat(competitionDto.getDescription(), is(COMPETITION_DESC_1));
        assertThat(competitionDto.getOrganizer(), is(ORGANIZER_1));
        assertThat(competitionDto.getPlace(), is(PLACE_1));
    }

    @Test(expected = CompetitionNotFoundException.class)
    public void deleteCompetitionIdNotExistsShouldReturnError() {
        // When
        competitionService.deleteCompetition(ID_1);
        // Assert
        verify(competitionRepository, never()).deleteById(ID_1);
    }

    @Test
    public void deleteCompetitionOK() {
        // Given
        // When
        when(competitionRepository.findCompetitionById(ID_1)).thenReturn(Competition.builder().build());

        // Assert
        competitionService.deleteCompetition(ID_1);
        verify(competitionRepository, times(1)).deleteById(ID_1);
    }

    @Test
    public void getCompetitionsNoCompetitionsShouldReturnEmptyList() {
        // Given
        // When
        // Assert
        List<CompetitionDto> competitions = competitionService.getCompetitions();

        assertNotNull(competitions);
        assertThat(competitions.size(), is(0));
    }

    @Test
    public void getCompetitionsShouldReturnCompetitionList() {
        // Given
        List<Competition> competitions = Arrays.asList(Competition.builder().id(ID_1).description(COMPETITION_DESC_1).beginDate(new Date(DAY_0)).endDate(new Date(DAY_1)).organizer(ORGANIZER_1).place(PLACE_1).build(),
                Competition.builder().id(ID_2).description(COMPETITION_DESC_2).beginDate(new Date(DAY_0)).endDate(new Date(DAY_1)).organizer(ORGANIZER_2).place(PLACE_2).build());

        // When
        when(competitionRepository.findAll()).thenReturn(competitions);

        // Assert
        List<CompetitionDto> competitionDtos = competitionService.getCompetitions();

        assertNotNull(competitionDtos);
        assertThat(competitions.size(), is(2));
        assertThat(competitionDtos.get(0).getId(), is(ID_1));
        assertThat(competitionDtos.get(0).getDescription(), is(COMPETITION_DESC_1));
        assertThat(competitionDtos.get(0).getOrganizer(), is(ORGANIZER_1));
        assertThat(competitionDtos.get(0).getPlace(), is(PLACE_1));
        assertThat(competitionDtos.get(0).getBeginDate(), is(new Date(DAY_0)));
        assertThat(competitionDtos.get(0).getEndDate(), is(new Date(DAY_1)));

        assertThat(competitionDtos.get(1).getId(), is(ID_2));
        assertThat(competitionDtos.get(1).getDescription(), is(COMPETITION_DESC_2));
        assertThat(competitionDtos.get(1).getOrganizer(), is(ORGANIZER_2));
        assertThat(competitionDtos.get(1).getPlace(), is(PLACE_2));
        assertThat(competitionDtos.get(1).getBeginDate(), is(new Date(DAY_0)));
        assertThat(competitionDtos.get(1).getEndDate(), is(new Date(DAY_1)));
    }

    @Test(expected = CompetitionNotFoundException.class)
    public void testCompetitionByIdNotExists() {
        CompetitionDto competitionDto = competitionService.getCompetition(ID_1);
    }

    @Test
    public void testCompetitionByIdOK() {
        // Given
        // When
        when(competitionRepository.findCompetitionById(ID_1))
                .thenReturn(Competition.builder()
                        .id(ID_1)
                        .place(PLACE_1)
                        .organizer(ORGANIZER_1)
                        .beginDate(new Date(DAY_0))
                        .endDate(new Date(DAY_1))
                        .description(COMPETITION_DESC_1)
                        .build());

        // Assert
        CompetitionDto competitionDto = competitionService.getCompetition(ID_1);

        assertNotNull(competitionDto);
        assertThat(competitionDto.getId(), is(ID_1));
        assertThat(competitionDto.getPlace(), is(PLACE_1));
        assertThat(competitionDto.getOrganizer(), is(ORGANIZER_1));
        assertThat(competitionDto.getDescription(), is(COMPETITION_DESC_1));
        assertThat(competitionDto.getBeginDate(), is(new Date(DAY_0)));
        assertThat(competitionDto.getEndDate(), is(new Date(DAY_1)));
    }

    @After
    public void cleanup() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @TestConfiguration
    static class CompetitionServiceImplTestContextConfiguration {
        @Bean
        public CompetitionService competitionService() {
            return new CompetitionServiceImpl();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(new ClassPathResource("application.yml"));
            propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
            return propertySourcesPlaceholderConfigurer;
        }
    }
}