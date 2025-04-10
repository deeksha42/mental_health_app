package com.example.habittracker.controller;

import com.example.habittracker.model.Habit;
import com.example.habittracker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    @Autowired
    private HabitService habitService;
    @PostMapping("/add")
    public ResponseEntity<?> addHabit(@RequestBody Habit habit) {
        try {
            return ResponseEntity.ok(habitService.addHabit(habit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to add habit: " + e.getMessage()));
        }
    }

    //@PostMapping("/add")
    //public Habit addHabit(@RequestBody Habit habit) {
    //    return habitService.addHabit(habit);
    //}

    @PostMapping("/update")
    public Map<String, Boolean> updateHabit(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String date = request.get("date");
        habitService.markHabitCompleted(name, date);
        return Map.of("success", true);
    }

    @GetMapping
    public Iterable<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }
}
