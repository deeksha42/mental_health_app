package com.example.journal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MeditationController {

    @GetMapping("/meditation")
    public String showMeditationForm() {
        return "meditation_form";
    }

    @PostMapping("/meditation/start")
    public String startMeditation(
            @RequestParam("type") String type,
            @RequestParam("duration") int duration,
            Model model) {

        System.out.println("[DEBUG] POST /meditation/start");
        System.out.println("Type: " + type);
        System.out.println("Duration: " + duration);

        model.addAttribute("type", type);
        model.addAttribute("duration", duration);
        return "meditation_play";
    }
}
