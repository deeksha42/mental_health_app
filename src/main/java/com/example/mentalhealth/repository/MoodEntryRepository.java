package com.example.mentalhealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mentalhealth.model.MoodEntry;
import java.time.LocalDate;
import java.util.List;

public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {
    List<MoodEntry> findByDate(LocalDate date);
}
