package com.example.mentalhealth.repository;

import com.example.mentalhealth.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
}
