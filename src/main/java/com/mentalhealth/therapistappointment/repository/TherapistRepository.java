package com.mentalhealth.therapistappointment.repository;

import com.mentalhealth.therapistappointment.model.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    List<Therapist> findByName(String name);
}