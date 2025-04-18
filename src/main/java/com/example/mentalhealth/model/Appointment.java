package com.example.mentalhealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    public enum Status {
        SCHEDULED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private LocalDateTime dateTime;           // scheduled time
    private LocalDateTime sessionStartTime;   // actual start time

    private Integer duration = 0; // in minutes

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    // Constructors
    public Appointment() {}

    public Appointment(String clientName, LocalDateTime dateTime, Therapist therapist) {
        this.clientName = clientName;
        this.dateTime = dateTime;
        this.therapist = therapist;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    // Business logic methods
    public void startSession() {
        this.sessionStartTime = LocalDateTime.now();  // system time
        this.status = Status.IN_PROGRESS;
    }

    public void completeSession(int durationMinutes) {
        this.duration = durationMinutes;
        this.status = Status.COMPLETED;
    }

    public void cancelAppointment() {
        this.status = Status.CANCELLED;
    }

    public boolean isScheduled() {
        return this.status == Status.SCHEDULED;
    }

    public boolean isInProgress() {
        return this.status == Status.IN_PROGRESS;
    }

    public boolean isCompleted() {
        return this.status == Status.COMPLETED;
    }
}
