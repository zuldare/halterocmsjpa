package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.athlete.AthleteDto;
import com.jho.halterocmsjpa.entity.Athlete;
import com.jho.halterocmsjpa.enums.GenderType;
import com.jho.halterocmsjpa.exception.GenderNotExistsException;
import com.jho.halterocmsjpa.repository.AthleteRepository;
import com.jho.halterocmsjpa.service.impl.AthleteServiceImpl;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AthleteServiceTest {

    private static final Integer ID_1 = 1;
    private static final Integer ID_2 = 2;
    private static final String NAME_1 = "Juan";
    private static final String NAME_2 = "Pepe";
    private static final String SURNAME_1 = "Nadie";
    private static final String SURNAME_2 = "Nadie Nadie";
    private static final Integer BIRTH_YEAR_1981 = 1981;
    private static final Integer BIRTH_YEAR_1995 = 1995;
    private static final String MALE_DESC = "MALE";
    private static final String FEMALE_DESC = "FEMALE";
    private static final String FEMALE_MISSPELLED = "FemalE";
    private static final String WRONG_GENDER = "ULTRA";

    @Autowired
    private AthleteService athleteService;

    @MockBean
    private AthleteRepository athleteRepository;

    @Test
    public void getAllAthletesNoExistingShouldReturnEmptyList() {
        // Given
        // When
        // Assert
        List<AthleteDto> athletes = athleteService.getAthletes(Optional.empty());

        assertNotNull(athletes);
        assertThat(athletes.size(), is(0));
    }

    @Test(expected = GenderNotExistsException.class)
    public void getAthletesByWrongGenderShouldReturnException() {
        // Given
        List<Athlete> athletes = Arrays.asList(Athlete.builder().id(ID_2).name(NAME_2).surname(SURNAME_2).gender(GenderType.FEMALE.getValue()).birthYear(BIRTH_YEAR_1995).build());

        // When
        when(athleteRepository.findAllByGender(GenderType.FEMALE.getValue())).thenReturn(athletes);

        // Assert
        List<AthleteDto> athleteDtos = athleteService.getAthletes(Optional.of(WRONG_GENDER));
        verify(athleteRepository, never()).findAllByGender(any());
        verify(athleteRepository, never()).findAll();

    }

    @Test
    public void getFemaleAthletesShouldReturnOnlyFemaleAthletes() {
        // Given
        List<Athlete> athletes = Arrays.asList(Athlete.builder().id(ID_2).name(NAME_2).surname(SURNAME_2).gender(GenderType.FEMALE.getValue()).birthYear(BIRTH_YEAR_1995).build());

        // When
        when(athleteRepository.findAllByGender(GenderType.FEMALE.getValue())).thenReturn(athletes);

        // Assert
        List<AthleteDto> athleteDtos = athleteService.getAthletes(Optional.of(FEMALE_DESC));
        verify(athleteRepository, times(1)).findAllByGender(GenderType.FEMALE.getValue());
        verify(athleteRepository, never()).findAll();

        assertThat(athleteDtos.get(0).getId(), is(ID_2));
        assertThat(athleteDtos.get(0).getName(), is(NAME_2));
        assertThat(athleteDtos.get(0).getSurname(), is(SURNAME_2));
        assertThat(athleteDtos.get(0).getBirthYear(), is(BIRTH_YEAR_1995));
        assertThat(athleteDtos.get(0).getGender(), is(GenderType.FEMALE.getValue()));
    }

    @Test
    public void getFemaleAthletesMisspelledGenderShouldReturnOnlyFemaleAthletes() {
        // Given
        List<Athlete> athletes = Arrays.asList(Athlete.builder().id(ID_2).name(NAME_2).surname(SURNAME_2).gender(GenderType.FEMALE.getValue()).birthYear(BIRTH_YEAR_1995).build());

        // When
        when(athleteRepository.findAllByGender(GenderType.FEMALE.getValue())).thenReturn(athletes);

        // Assert
        List<AthleteDto> athleteDtos = athleteService.getAthletes(Optional.of(FEMALE_MISSPELLED));
        verify(athleteRepository, times(1)).findAllByGender(GenderType.FEMALE.getValue());
        verify(athleteRepository, never()).findAll();

        assertThat(athleteDtos.get(0).getId(), is(ID_2));
        assertThat(athleteDtos.get(0).getName(), is(NAME_2));
        assertThat(athleteDtos.get(0).getSurname(), is(SURNAME_2));
        assertThat(athleteDtos.get(0).getBirthYear(), is(BIRTH_YEAR_1995));
        assertThat(athleteDtos.get(0).getGender(), is(GenderType.FEMALE.getValue()));
    }

    @Test
    public void getAthletesShouldReturnAthleteList() {
        // Given
        List<Athlete> athletes = Arrays.asList(Athlete.builder().id(ID_1).name(NAME_1).surname(SURNAME_1).gender(GenderType.MALE.getValue()).birthYear(BIRTH_YEAR_1981).build(),
                Athlete.builder().id(ID_2).name(NAME_2).surname(SURNAME_2).gender(GenderType.FEMALE.getValue()).birthYear(BIRTH_YEAR_1995).build());

        // When
        when(athleteRepository.findAll()).thenReturn(athletes);

        // Assert
        List<AthleteDto> athleteDtos = athleteService.getAthletes(Optional.empty());
        verify(athleteRepository, never()).findAllByGender(any());
        verify(athleteRepository, times(1)).findAll();

        assertNotNull(athleteDtos);
        assertThat(athleteDtos.size(), is(2));
        assertThat(athleteDtos.get(0).getId(), is(ID_1));
        assertThat(athleteDtos.get(0).getName(), is(NAME_1));
        assertThat(athleteDtos.get(0).getSurname(), is(SURNAME_1));
        assertThat(athleteDtos.get(0).getBirthYear(), is(BIRTH_YEAR_1981));
        assertThat(athleteDtos.get(0).getGender(), is(GenderType.MALE.getValue()));

        assertThat(athleteDtos.get(1).getId(), is(ID_2));
        assertThat(athleteDtos.get(1).getName(), is(NAME_2));
        assertThat(athleteDtos.get(1).getSurname(), is(SURNAME_2));
        assertThat(athleteDtos.get(1).getBirthYear(), is(BIRTH_YEAR_1995));
        assertThat(athleteDtos.get(1).getGender(), is(GenderType.FEMALE.getValue()));
    }

    @TestConfiguration
    static class AthleteServiceImplTestContextConfiguration {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public AthleteService athleteService() {
            return new AthleteServiceImpl();
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
