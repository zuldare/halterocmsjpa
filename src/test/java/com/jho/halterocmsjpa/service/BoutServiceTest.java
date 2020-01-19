package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.bout.BoutDto;
import com.jho.halterocmsjpa.repository.BoutRepository;
import com.jho.halterocmsjpa.repository.CompetitionRepository;
import com.jho.halterocmsjpa.service.impl.BoutServiceImpl;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class BoutServiceTest {

    @Autowired
    private BoutService boutService;

    @MockBean
    private BoutRepository boutRepository;

    @MockBean
    private CompetitionRepository competitionRepository;


    @Test
    public void getBoutsWhenNoOneExistsShouldReturnEmpty() {
        List<BoutDto> bouts = boutService.getBouts();
        assertNotNull(bouts);
        assertThat(bouts.size(), is(0));
    }

    @TestConfiguration
    static class BoutServiceImplTestContextConfiguration {
        @Bean
        public BoutService boutService() {
            return new BoutServiceImpl();
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
