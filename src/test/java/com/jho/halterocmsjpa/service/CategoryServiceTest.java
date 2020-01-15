package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.category.CategoryDto;
import com.jho.halterocmsjpa.entity.Category;
import com.jho.halterocmsjpa.enums.GenderType;
import com.jho.halterocmsjpa.repository.CategoryRepository;
import com.jho.halterocmsjpa.service.impl.CategoryServiceImpl;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    private static final String CATEGORY_M110 = "Male 110kg";
    private static final String MALE_DESC = "MALE";
    private static final Integer ID_1 = 1;
    private static final BigDecimal MIN_110 = new BigDecimal(110);
    private static final BigDecimal MAX_110 = new BigDecimal(300);
    private static final String SHORT_DESC_110 = "M110";

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void getAllCategoriesNonExistingShouldReturnEmptyList() {
        // Given
        // When
        // Assert
        List<CategoryDto> categories = categoryService.getCategories();

        assertNotNull(categories);
        assertThat(categories.size(), is(0));
    }

    @Test
    public void getAllCategoriesShouldReturnOK() {
        // Given
        List<Category> categories = Arrays.asList(Category.builder().id(ID_1).description(CATEGORY_M110).gender(GenderType.MALE.getValue())
                .initialWeight(MIN_110).finalWeight(MAX_110).shortDescription(SHORT_DESC_110).build());
        // When
        when(categoryRepository.findAll()).thenReturn(categories);
        // Assert
        List<CategoryDto> categoriesDto = categoryService.getCategories();

        assertNotNull(categoriesDto);
        assertThat(categoriesDto.size(), is(1));

        assertThat(categoriesDto.get(0).getId(), is(ID_1));
        assertThat(categoriesDto.get(0).getDescription(), is(CATEGORY_M110));
        assertThat(categoriesDto.get(0).getGender(), is(GenderType.MALE.getValue()));
        assertThat(categoriesDto.get(0).getInitialWeight(), is(MIN_110));
        assertThat(categoriesDto.get(0).getFinalWeight(), is(MAX_110));
    }

    @TestConfiguration
    static class CategoryServiceImplTestConfiguration {
        @Bean
        public CategoryService categoryService() {
            return new CategoryServiceImpl();
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
