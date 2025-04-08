package com.mentalhealth.therapistappointment.util;

import com.mentalhealth.therapistappointment.model.Appointment;
import com.mentalhealth.therapistappointment.model.Therapist;

import java.time.LocalDateTime;

public class AppointmentFactory {
    public static Appointment createAppointment(String clientName, LocalDateTime dateTime,
                                                Therapist therapist, Appointment.Status status) {
        Appointment appointment = new Appointment(clientName, dateTime, therapist);
        appointment.setStatus(status);
        return appointment;
    }
}
