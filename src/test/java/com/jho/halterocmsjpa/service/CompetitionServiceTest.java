package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.dto.competition.CompetitionDto;
import com.jho.halterocmsjpa.entity.Competition;
import com.jho.halterocmsjpa.exception.CompetitionAlreadyExists;
import com.jho.halterocmsjpa.exception.CompetitionNotExists;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CompetitionServiceTest {

    private static final String COMPETITION_DESC = "Diktator Cup";
    private static final String ORGANIZER = "Tracius";
    private static final String PLACE = "Calle Industrias 32, Alcorcón";
    private static final Long DAY_0 = 0L;   //1970-01-01
    private static final Long DAY_1 = 86400L; //1970-01-02
    private static final Integer ID_1 = 1;

    @MockBean
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionService competitionService;

    @Test(expected = CompetitionAlreadyExists.class)
    public void createCompetitionAlreadyExistsShouldThrowException() {
        DateTimeUtils.setCurrentMillisFixed(0L);

        //Given
        CompetitionCreateDto competitionCreateDto = CompetitionCreateDto.builder()
                .beginDate(DAY_0)
                .endDate(DAY_1)
                .description(COMPETITION_DESC)
                .organizer(ORGANIZER)
                .place(PLACE)
                .build();

        //When
        when(competitionRepository.findCompetitionByDescriptionAndBeginDate(anyString(), anyLong()))
                .thenReturn(Competition.builder()
                        .description(COMPETITION_DESC)
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
                .description(COMPETITION_DESC)
                .organizer(ORGANIZER)
                .place(PLACE)
                .build();

        //When
        when(competitionRepository.save(any(Competition.class)))
                .thenReturn(Competition.builder()
                        .description(COMPETITION_DESC)
                        .beginDate(new Date(DAY_0))
                        .endDate(new Date(DAY_1))
                        .organizer(ORGANIZER)
                        .place(PLACE)
                        .id(ID_1)
                        .build());

        // Assert
        CompetitionDto competitionDto = competitionService.createCompetition(competitionCreateDto);

        assertNotNull(competitionDto);
        assertNotNull(competitionDto.getId());
        assertThat(competitionDto.getBeginDate(), is(new Date(DAY_0)));
        assertThat(competitionDto.getEndDate(), is(new Date(DAY_1)));
        assertThat(competitionDto.getDescription(), is(COMPETITION_DESC));
        assertThat(competitionDto.getOrganizer(), is(ORGANIZER));
        assertThat(competitionDto.getPlace(), is(PLACE));
    }

    @Test(expected = CompetitionNotExists.class)
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