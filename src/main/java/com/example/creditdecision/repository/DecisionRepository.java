
package com.example.creditdecision.repository;

import com.example.creditdecision.model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<Decision, Long> {}
