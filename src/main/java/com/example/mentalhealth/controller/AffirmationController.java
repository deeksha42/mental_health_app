package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.Affirmation;
import com.example.mentalhealth.service.AffirmationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affirmations")
@CrossOrigin(origins = "*")
public class AffirmationController {

    private final AffirmationService affirmationService;

    public AffirmationController(AffirmationService affirmationService) {
        this.affirmationService = affirmationService;
    }

    @GetMapping("/all")
    public List<Affirmation> getAllValidAffirmations() {
        return affirmationService.getPastAndTodayAffirmations();
    }
}
