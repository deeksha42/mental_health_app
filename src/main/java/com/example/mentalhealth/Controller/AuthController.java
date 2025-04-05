package com.example.mentalhealth.Controller;

import com.example.mentalhealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.authenticateUser(username, password)) {
            return "redirect:/dashboard"; // Redirect to dashboard on success
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // Show signup page
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.registerUser(username, password)) {
            return "redirect:/login"; // Redirect to login after successful signup
        }
        model.addAttribute("error", "Username already exists!");
        return "signup";
    }

    // Show dashboard (Home Page)
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    // Navigation to other sections
    @GetMapping("/mood-tracking")
    public String moodTracking() {
        return "mood-tracking";
    }

    @GetMapping("/journaling")
    public String journaling() {
        return "journaling";
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
    public String resources() {
        return "resources";
    }

    @GetMapping("/analytics")
    public String analytics() {
        return "analytics";
    }

    @GetMapping("/meditation")
    public String meditation() {
        return "meditation";
    }

    @GetMapping("/affirmations")
    public String affirmations() {
        return "affirmations";
    }

    @GetMapping("/mood-history")
    public String moodHistory() {
        return "mood-history";
    }
}
