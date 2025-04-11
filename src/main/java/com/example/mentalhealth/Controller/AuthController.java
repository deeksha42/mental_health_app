package com.example.mentalhealth.controller;

import com.example.mentalhealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Login
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.authenticateUser(username, password)) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // Signup
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.registerUser(username, password)) {
            return "redirect:/login";
        }
        model.addAttribute("error", "Username already exists!");
        return "signup";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    // Navigation Pages
    @GetMapping("/mood-tracking")
    public String moodTracking() {
        return "mood-tracking";
    }

    
    

    @GetMapping("/appointments")
    public String appointments() {
        return "appointments";
    }

    @GetMapping("/habits")
    public String habits() {
        return "habits";
    }

    @GetMapping("/resources")
    public String getResources(Model model) {
        model.addAttribute("resources", List.of(
            new Resource("Mindfulness Meditation Guide", "A beginner's guide to mindfulness meditation", "https://www.mindful.org/meditation/mindfulness-getting-started/", "Meditation"),
            new Resource("Coping with Anxiety", "Strategies for managing anxiety symptoms", "https://www.healthline.com/health/mental-health/how-to-cope-with-anxiety", "Anxiety"),
            new Resource("Depression Self-Help", "Self-help tips for depression", "https://psychcentral.com/depression/self-help-for-depression", "Depression"),
            new Resource("Breathing Exercises", "Simple breathing techniques for stress relief", "https://www.health.harvard.edu/mind-and-mood/relaxation-techniques-breath-control-helps-quell-errant-stress-response", "Stress Relief"),
            new Resource("Sleep Hygiene Tips", "Improve your sleep with these simple tips", "https://www.sleepfoundation.org/sleep-hygiene", "Sleep")
        ));
        return "resources";
    }

    @GetMapping("/analytics")
    public String analytics() {
        return "analytics";
    }

    

    @GetMapping("/affirmations")
    public String affirmations() {
        return "affirmations";
    }

    @GetMapping("/mood-history")
    public String moodHistory() {
        return "mood-history";
    }

    // Inner static class for Resource
    private static class Resource {
        private final String title;
        private final String description;
        private final String url;
        private final String category;

        public Resource(String title, String description, String url, String category) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.category = category;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getUrl() { return url; }
        public String getCategory() { return category; }
    }
}
