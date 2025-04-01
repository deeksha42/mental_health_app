package com.example.journal.repository;

import com.example.journal.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
}
