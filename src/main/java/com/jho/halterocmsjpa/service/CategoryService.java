package com.jho.halterocmsjpa.service;

import com.jho.halterocmsjpa.dto.category.CategoryBodyWeightGenderRequestDto;
import com.jho.halterocmsjpa.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * Get all categories.
     *
     * @return a list containing all the categories.
     */
    List<CategoryDto> getCategories();

    /**
     * Returns a category based on gender and bodyweight.
     *
     * @param categoryBodyWeightGenderRequest dto containing filtering information.
     * @return the category matching the criteria.
     */
    CategoryDto getCategoryByGenderAndBodyWeight(CategoryBodyWeightGenderRequestDto categoryBodyWeightGenderRequest);
}
