package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.Habit;
import com.example.mentalhealth.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    // Serve the index.html page
    @GetMapping("/page")
    public String habitPage() {
        return "habit"; // This resolves to src/main/resources/templates/index.html
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addHabit(@RequestBody Habit habit) {
        try {
            return ResponseEntity.ok(habitService.addHabit(habit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to add habit: " + e.getMessage()));
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Boolean> updateHabit(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String date = request.get("date");
        habitService.markHabitCompleted(name, date);
        return Map.of("success", true);
    }

    @GetMapping
    @ResponseBody
    public Iterable<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }
}
