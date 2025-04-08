package com.mentalhealth.therapistappointment.controller;

import com.mentalhealth.therapistappointment.model.Appointment;
import com.mentalhealth.therapistappointment.model.Therapist;
import com.mentalhealth.therapistappointment.repository.AppointmentRepository;
import com.mentalhealth.therapistappointment.repository.TherapistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final TherapistRepository therapistRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
                               TherapistRepository therapistRepository) {
        this.appointmentRepository = appointmentRepository;
        this.therapistRepository = therapistRepository;
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestParam Long therapistId,
                                @RequestParam String clientName,
                                @RequestParam String appointmentDate,
                                @RequestParam String timeSlot) {
        Optional<Therapist> therapist = therapistRepository.findById(therapistId);
        if (therapist.isPresent()) {
            LocalDate date = LocalDate.parse(appointmentDate);
            LocalTime time = LocalTime.parse(timeSlot);
            LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);

            Appointment appointment = AppointmentFactory.createAppointment(
                clientName,
                appointmentDateTime,
                therapist.get(),
                Appointment.Status.SCHEDULED
            );
            appointmentRepository.save(appointment);
        }
        return "redirect:/appointments/history?clientName=" + clientName;
    }

    @GetMapping("/history")
    public String getAppointments(@RequestParam String clientName, Model model) {
        List<Appointment> appointments = appointmentRepository.findByClientName(clientName);
        model.addAttribute("appointments", appointments);
        model.addAttribute("clientName", clientName);
        return "appointments";
    }

    @GetMapping("/view/{id}")
    public String viewAppointment(@PathVariable Long id, Model model) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            model.addAttribute("appointment", appointmentOpt.get());
            return "appointment-details";
        }
        return "redirect:/appointments/history";
    }

    @PostMapping("/start/{id}")
    public String startSession(@PathVariable Long id, Model model) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setSessionStartTime(LocalDateTime.now()); // record actual session start time
            appointment.startSession();
            appointmentRepository.save(appointment);
            model.addAttribute("appointment", appointment);
            return "session";
        }
        return "redirect:/appointments/history";
    }

    @PostMapping("/stop/{id}")
    public String stopSession(@PathVariable Long id) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime start = appointment.getSessionStartTime();
            if (start == null) {
                return "redirect:/appointments/history?clientName=" + appointment.getClientName();
            }

            long duration = Duration.between(start, now).toMinutes();
            appointment.completeSession((int) duration);
            appointmentRepository.save(appointment);

            return "redirect:/appointments/history?clientName=" + appointment.getClientName();
        }

        return "redirect:/appointments/history";
    }
}

class AppointmentFactory {
    public static Appointment createAppointment(String clientName, LocalDateTime dateTime,
                                              Therapist therapist, Appointment.Status status) {
        Appointment appointment = new Appointment(clientName, dateTime, therapist);
        appointment.setStatus(status);
        return appointment;
    }
}