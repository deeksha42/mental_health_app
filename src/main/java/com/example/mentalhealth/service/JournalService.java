package com.example.mentalhealth.service;

import com.example.mentalhealth.model.EntryStatus;
import com.example.mentalhealth.model.JournalEntry;
import com.example.mentalhealth.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public boolean isEditable(JournalEntry entry) {
        LocalDate today = LocalDate.now();
        boolean editable = entry.getStatus() == EntryStatus.DRAFT &&
                           entry.getDate().isEqual(today);

        // If the entry is stale (not editable and still in DRAFT), auto-finalize
        if (!editable && entry.getStatus() == EntryStatus.DRAFT) {
            entry.setStatus(EntryStatus.SAVED);
            repo.save(entry);
            System.out.println("[Auto-Finalized] Entry ID " + entry.getId() + " marked as SAVED.");
        }

        return editable;
    }
}
