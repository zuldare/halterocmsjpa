package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Category repository layer.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
