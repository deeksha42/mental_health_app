package com.example.mentalhealth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mood_entries")
public class MoodEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mood;
    private int sleep;
    private int energy;
    private int stress;
    private String notes;
    private LocalDate date;

    public MoodEntry() {}

    public MoodEntry(String mood, int sleep, int energy, int stress, String notes, LocalDate date) {
        this.mood = mood;
        this.sleep = sleep;
        this.energy = energy;
        this.stress = stress;
        this.notes = notes;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public int getSleep() { return sleep; }
    public void setSleep(int sleep) { this.sleep = sleep; }

    public int getEnergy() { return energy; }
    public void setEnergy(int energy) { this.energy = energy; }

    public int getStress() { return stress; }
    public void setStress(int stress) { this.stress = stress; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
