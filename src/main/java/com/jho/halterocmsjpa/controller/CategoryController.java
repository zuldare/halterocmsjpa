package com.jho.halterocmsjpa.controller;

import com.jho.halterocmsjpa.dto.category.CategoryDto;
import com.jho.halterocmsjpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Category controller layer.
 */
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Get all categories.
     *
     * @return a list containing all the categories.
     */
    @GetMapping("/categories")
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

}
