package com.mentalhealth.therapistappointment.service;

import com.mentalhealth.therapistappointment.model.Appointment;
import com.mentalhealth.therapistappointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment scheduleAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
