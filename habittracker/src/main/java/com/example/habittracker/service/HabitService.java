package com.example.habittracker.service;

import com.example.habittracker.model.Habit;
import com.example.habittracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;

    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void markHabitCompleted(String name, String date) {
        Optional<Habit> habitOptional = habitRepository.findByName(name);
        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();
            habit.markCompleted(date);
            habitRepository.save(habit);
        }
    }

    public Iterable<Habit> getAllHabits() {
        return habitRepository.findAll();
    }
}
