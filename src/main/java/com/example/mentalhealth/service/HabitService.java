package com.example.mentalhealth.service;

import com.example.mentalhealth.model.Habit;
import com.example.mentalhealth.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitService {
    private static HabitService instance;
    
    @Autowired
    private HabitRepository habitRepository;
    
    // List of observers (for Observer pattern)
    private List<HabitObserver> observers = new ArrayList<>();
    
    private HabitService() {
        // Private constructor for Singleton
    }
    
    // Singleton getInstance method
    public static synchronized HabitService getInstance() {
        if (instance == null) {
            instance = new HabitService();
        }
        return instance;
    }

    public Habit addHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void markHabitCompleted(String name, String date) {
        Optional<Habit> habitOptional = habitRepository.findByName(name);
        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();
            habit.markCompleted(date);
            habitRepository.save(habit);
            
            // Notify observers when habit is completed
            notifyObservers(habit, date);
        }
    }
    
    // Observer pattern methods
    public void addObserver(HabitObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(HabitObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(Habit habit, String date) {
        for (HabitObserver observer : observers) {
            observer.habitCompleted(habit, date);
        }
    }

    public Iterable<Habit> getAllHabits() {
        return habitRepository.findAll();
    }
    
    // Observer interface
    public interface HabitObserver {
        void habitCompleted(Habit habit, String date);
    }

    // Add this method to HabitService.java
    public int getHabitStreak(String name) {
        Optional<Habit> habit = habitRepository.findByName(name);
        return habit.map(Habit::getCurrentStreak).orElse(0);
    }
}
