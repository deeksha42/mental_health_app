package com.mentalhealth.therapistappointment.controller;

import com.mentalhealth.therapistappointment.model.Appointment;
import com.mentalhealth.therapistappointment.model.Therapist;
import com.mentalhealth.therapistappointment.repository.AppointmentRepository;
import com.mentalhealth.therapistappointment.repository.TherapistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/therapist")
public class TherapistAuthController {

    private final TherapistRepository therapistRepository;
    private final AppointmentRepository appointmentRepository;

    public TherapistAuthController(TherapistRepository therapistRepository, 
                                 AppointmentRepository appointmentRepository) {
        this.therapistRepository = therapistRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "therapist-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, Model model) {
        List<Therapist> therapists = therapistRepository.findByName(name);
        if (therapists.isEmpty()) {
            model.addAttribute("error", "Therapist not found");
            return "therapist-login";
        }
        Therapist therapist = therapists.get(0);
        return "redirect:/therapist/dashboard?id=" + therapist.getId();
    }

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam Long id, Model model) {
        Therapist therapist = therapistRepository.findById(id).orElseThrow();
        List<Appointment> appointments = appointmentRepository.findByTherapistId(id);
        
        model.addAttribute("therapist", therapist);
        model.addAttribute("appointments", appointments);
        return "therapist-dashboard";
    }
}