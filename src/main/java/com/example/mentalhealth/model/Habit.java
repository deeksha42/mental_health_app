package com.example.mentalhealth.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int goal;

    @ElementCollection
    @CollectionTable(name = "habit_completed_dates", joinColumns = @JoinColumn(name = "habit_id"))
    @Column(name = "date")
    private List<String> completedDates = new ArrayList<>();
    
    // Decorator pattern - additional behavior
    @Transient
    private HabitDecorator decorator;

    public Habit() {}

    public Habit(String name, int goal) {
        this.name = name;
        this.goal = goal;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getGoal() { return goal; }
    public List<String> getCompletedDates() { return completedDates; }

    public void setName(String name) { this.name = name; }
    public void setGoal(int goal) { this.goal = goal; }
    
    // Decorator methods
    public void setDecorator(HabitDecorator decorator) {
        this.decorator = decorator;
    }
    
    public int getCurrentStreak() {
        if (decorator != null) {
            return decorator.calculateStreak(this);
        }
        return 0; // Default behavior when no decorator is set
    }

    public void markCompleted(String date) {
        if (!completedDates.contains(date)) {
            completedDates.add(date);
        }
    }
    
    // Decorator interface
    public interface HabitDecorator {
        int calculateStreak(Habit habit);
    }
    
    // Basic decorator implementation
    public static class BasicStreakDecorator implements HabitDecorator {
        @Override
        public int calculateStreak(Habit habit) {
            return habit.getCompletedDates().size();
        }
    }
}
