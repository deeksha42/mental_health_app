package com.example.mentalhealth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Affirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String quote;

    public Affirmation() {
    }

    public Affirmation(LocalDate date, String quote) {
        this.date = date;
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getQuote() {
        return quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
