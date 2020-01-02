package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.competition.CompetitionCreateDto;
import com.jho.halterocmsjpa.service.impl.CompetitionServiceImpl;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CompetitionServiceTest {

    private static final String DESC = "Diktator Cup";
    private static final String ORGANIZER = "Tracius";
    private static final String PLACE = "";

    public void createCompetitionTestOK() {
        DateTimeUtils.setCurrentMillisFixed(0L);
        //Given
        CompetitionCreateDto.builder()
                .beginDate()
                .endDate()
                .description()
                .organizer()
                .place()
                .build();
        //When

        //Assert
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
