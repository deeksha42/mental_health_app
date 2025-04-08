package com.mentalhealth.therapistappointment.repository;

import com.mentalhealth.therapistappointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientName(String clientName);
    List<Appointment> findByTherapistId(Long therapistId);
}