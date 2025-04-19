package com.example.mentalhealth.service;

import com.example.mentalhealth.model.Affirmation;
import com.example.mentalhealth.repository.AffirmationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AffirmationService {

    private final AffirmationRepository affirmationRepository;

    public AffirmationService(AffirmationRepository affirmationRepository) {
        this.affirmationRepository = affirmationRepository;
    }

    public List<Affirmation> getPastAndTodayAffirmations() {
        return affirmationRepository.findByDateLessThanEqualOrderByDateDesc(LocalDate.now());
    }
}

