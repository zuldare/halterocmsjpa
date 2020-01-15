package com.jho.halterocmsjpa.service.impl;

import com.jho.halterocmsjpa.dto.category.CategoryDto;
import com.jho.halterocmsjpa.entity.Category;
import com.jho.halterocmsjpa.repository.CategoryRepository;
import com.jho.halterocmsjpa.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Category service layer.
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Get all categories.
     *
     * @return a list containing all the categories.
     */
    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return modelMapper.map(categories, new TypeToken<List<CategoryDto>>() {
        }.getType());
    }
}
