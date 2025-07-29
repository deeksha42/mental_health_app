// HabitController.java
package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.Habit;
import com.example.mentalhealth.service.HabitService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.Map;

@Controller
@RequestMapping("/api/habits")
public class HabitController implements HabitService.HabitObserver {

    @Autowired
    private HabitService habitService;
    
    // Observer pattern - implement activity logging
    @PostConstruct
    public void init() {
        habitService.addObserver(this);
    }
    
    @Override
    public void habitCompleted(Habit habit, String date) {
        System.out.println("Logging: Habit '" + habit.getName() + "' completed on " + date);
        // Here you could log to database, send notifications, etc.
    }

    // Serve the index.html page
    @GetMapping("/page")
    public String habitPage() {
        return "habit"; // This resolves to src/main/resources/templates/index.html
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addHabit(@RequestBody Habit habit) {
        try {
            // Apply decorator pattern
            habit.setDecorator(new Habit.BasicStreakDecorator());
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
    
    @GetMapping("/streak/{name}")
    @ResponseBody
    public Map<String, Integer> getHabitStreak(@PathVariable String name) {
        return Map.of("streak", habitService.getHabitStreak(name));
    }

    @GetMapping
    @ResponseBody
    public Iterable<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }
}
