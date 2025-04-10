package com.example.habittracker.repository;

import com.example.habittracker.model.Habit;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface HabitRepository extends CrudRepository<Habit, Long> {
    Optional<Habit> findByName(String name);
}
