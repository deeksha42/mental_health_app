package com.mentalhealth.therapistappointment.controller;

import com.mentalhealth.therapistappointment.model.Therapist;
import com.mentalhealth.therapistappointment.repository.TherapistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class TherapistController {
    
    private final TherapistRepository therapistRepository;

    public TherapistController(TherapistRepository therapistRepository) {
        this.therapistRepository = therapistRepository;
    }

    @GetMapping("/therapists")
    public String getTherapists(Model model) {
        List<Therapist> therapists = therapistRepository.findAll();
        model.addAttribute("therapists", therapists);
        return "therapists"; // Redirects to 'therapists.html'
    }
}
