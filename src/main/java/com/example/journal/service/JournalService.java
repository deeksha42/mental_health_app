package com.example.journal.service;

import com.example.journal.model.EntryStatus;
import com.example.journal.model.JournalEntry;
import com.example.journal.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService {

    private final JournalRepository repo;

    public JournalService(JournalRepository repo) {
        this.repo = repo;
    }

    public List<JournalEntry> getAll() {
        return repo.findAll();
    }

    public JournalEntry getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public JournalEntry save(JournalEntry entry) {
        return repo.save(entry);
    }

    public boolean isEditable(JournalEntry entry) {
        LocalDate today = LocalDate.now();
        return entry.getStatus() == EntryStatus.DRAFT && entry.getDate().isEqual(today);
    }
}
