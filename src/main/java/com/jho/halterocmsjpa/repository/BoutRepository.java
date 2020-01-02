package com.jho.halterocmsjpa.repository;

import com.jho.halterocmsjpa.entity.Bout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Bout repository layer.
 */
@Repository
public interface BoutRepository extends JpaRepository<Bout, Integer> {
}
