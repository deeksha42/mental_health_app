package com.example.journal.controller;

import com.example.journal.model.EntryStatus;
import com.example.journal.model.JournalEntry;
import com.example.journal.service.JournalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/journals")
public class JournalController {

    private final JournalService service;

    public JournalController(JournalService service) {
        this.service = service;
    }

    @GetMapping
    public String listJournals(Model model) {
        model.addAttribute("entries", service.getAll());
        return "journal_list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        JournalEntry entry = new JournalEntry();
        entry.setStatus(EntryStatus.DRAFT);
        entry.setDate(LocalDate.now());
        entry.setCreatedAt(LocalDateTime.now());
        model.addAttribute("entry", entry);
        return "journal_form";
    }

    @GetMapping("/edit/{id}")
    public String editEntry(@PathVariable Long id, Model model) {
        JournalEntry entry = service.getById(id);
        if (entry != null && service.isEditable(entry)) {
            model.addAttribute("entry", entry);
            return "journal_form";
        }
        return "redirect:/journals/" + id + "?error=not-editable";
    }

    @PostMapping("/save")
    public String saveEntry(@ModelAttribute JournalEntry entry) {
        if (entry.getCreatedAt() == null) {
            entry.setCreatedAt(LocalDateTime.now());
        }
        if (entry.getStatus() == null) {
            entry.setStatus(EntryStatus.DRAFT);
        }
        if (entry.getDate() == null) {
            entry.setDate(LocalDate.now());
        }
        service.save(entry);
        return "redirect:/journals";
    }

    @GetMapping("/{id}")
    public String viewEntry(@PathVariable Long id, Model model) {
        JournalEntry entry = service.getById(id);
        model.addAttribute("entry", entry);
        model.addAttribute("editable", service.isEditable(entry));
        return "journal_view";
    }

    @PostMapping("/finalize/{id}")
    public String finalizeEntry(@PathVariable Long id) {
        JournalEntry entry = service.getById(id);
        if (entry != null && service.isEditable(entry)) {
            entry.setStatus(EntryStatus.SAVED);
            service.save(entry);
        }
        return "redirect:/journals/" + id;
    }
}