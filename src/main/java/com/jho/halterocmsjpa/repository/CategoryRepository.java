package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Category repository layer.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.gender = :gender and c.initialWeight < :bodyweight and :bodyWeight <= c.finalWeight")
    Category findCategoryByGenderAndBodyWeight(@Param("bodyWeight") BigDecimal bodyWeight, @Param("gender") Integer gender);
}
