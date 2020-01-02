package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.AthleteBout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AthleteBout repository layer.
 */
@Repository
public interface AthleteBoutRepository extends JpaRepository<AthleteBout, Integer> {
}
