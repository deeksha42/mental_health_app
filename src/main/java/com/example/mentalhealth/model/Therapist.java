package com.example.mentalhealth.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    private String availability;

    @ElementCollection
    private List<LocalTime> timeSlots;

    public Therapist() {}

    public Therapist(String name, String specialization, String availability, List<LocalTime> timeSlots) {
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
        this.timeSlots = timeSlots;
    }

    // Getters & Setters remain the same
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public List<LocalTime> getTimeSlots() { return timeSlots; }
    public void setTimeSlots(List<LocalTime> timeSlots) { this.timeSlots = timeSlots; }
}