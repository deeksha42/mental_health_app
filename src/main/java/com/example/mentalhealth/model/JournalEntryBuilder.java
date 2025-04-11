package com.example.mentalhealth.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JournalEntryBuilder {

    private final JournalEntry entry;

    private JournalEntryBuilder() {
        entry = new JournalEntry();
    }

    public static JournalEntryBuilder builder() {
        return new JournalEntryBuilder();
    }

    public JournalEntryBuilder id(Long id) {
        entry.setId(id);
        return this;
    }

    public JournalEntryBuilder title(String title) {
        entry.setTitle(title);
        return this;
    }

    public JournalEntryBuilder content(String content) {
        entry.setContent(content);
        return this;
    }

    public JournalEntryBuilder date(LocalDate date) {
        entry.setDate(date);
        return this;
    }

    public JournalEntryBuilder status(EntryStatus status) {
        entry.setStatus(status);
        return this;
    }

    public JournalEntryBuilder createdAt(LocalDateTime createdAt) {
        entry.setCreatedAt(createdAt);
        return this;
    }

    public JournalEntryBuilder privacy(String privacy) {
        entry.setPrivacySetting(privacy);
        return this;
    }

    public JournalEntry build() {
        return entry;
    }
}
