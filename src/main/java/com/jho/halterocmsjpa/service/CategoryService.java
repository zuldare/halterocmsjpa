package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * Get all categories.
     *
     * @return a list containing all the categories.
     */
    List<CategoryDto> getCategories();
}
