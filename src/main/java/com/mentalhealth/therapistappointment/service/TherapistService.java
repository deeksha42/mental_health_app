package com.mentalhealth.therapistappointment.service;

import com.mentalhealth.therapistappointment.model.Therapist;
import com.mentalhealth.therapistappointment.repository.TherapistRepository;
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
