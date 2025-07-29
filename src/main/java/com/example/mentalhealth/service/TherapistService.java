package com.example.mentalhealth.service;

import com.example.mentalhealth.model.Therapist;
import com.example.mentalhealth.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapistService {

    @Autowired
    private TherapistRepository therapistRepository;

    public List<Therapist> getAllTherapists() {
        return therapistRepository.findAll();
    }

    public Therapist getTherapistById(Long id) {
        return therapistRepository.findById(id).orElse(null);
    }
}
