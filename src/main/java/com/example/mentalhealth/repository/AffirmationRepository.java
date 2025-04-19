package com.example.mentalhealth.repository;

import com.example.mentalhealth.model.Affirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AffirmationRepository extends JpaRepository<Affirmation, Long> {
    List<Affirmation> findByDateLessThanEqualOrderByDateDesc(LocalDate date);
}


