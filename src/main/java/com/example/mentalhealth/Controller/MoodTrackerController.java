package com.example.mentalhealth.Controller;

import com.example.mentalhealth.model.MoodEntry;
import com.example.mentalhealth.repository.MoodEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mood")
@CrossOrigin(origins = "http://localhost:5500")  // Adjust for frontend
public class MoodTrackerController {

    @Autowired
    private MoodEntryRepository moodEntryRepository;

    @PostMapping("/save")
    public MoodEntry saveMoodEntry(@RequestBody MoodEntry moodEntry) {
        if (moodEntry.getDate() == null) {
            moodEntry.setDate(LocalDate.now());
        }
        return moodEntryRepository.save(moodEntry);
    }

    @GetMapping("/history/{date}")
    public List<MoodEntry> getMoodEntriesByDate(@PathVariable String date) {
        return moodEntryRepository.findByDate(LocalDate.parse(date));
    }

    @GetMapping("/all")
    public List<MoodEntry> getAllMoodEntries() {
        return moodEntryRepository.findAll();
    }
}
