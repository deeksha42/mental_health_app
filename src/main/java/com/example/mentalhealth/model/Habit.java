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

    public void markCompleted(String date) {
        if (!completedDates.contains(date)) {
            completedDates.add(date);
        }
    }
}
