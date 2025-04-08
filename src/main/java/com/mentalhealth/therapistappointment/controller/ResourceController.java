package com.mentalhealth.therapistappointment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ResourceController {

    @GetMapping("/resources")
    public String getResources(Model model) {
        // In a real app, these would come from a database
        model.addAttribute("resources", List.of(
            new Resource("Mindfulness Meditation Guide", "A beginner's guide to mindfulness meditation", "https://www.mindful.org/meditation/mindfulness-getting-started/", "Meditation"),
            new Resource("Coping with Anxiety", "Strategies for managing anxiety symptoms", "https://www.healthline.com/health/mental-health/how-to-cope-with-anxiety", "Anxiety"),
            new Resource("Depression Self-Help", "Self-help tips for depression", "https://psychcentral.com/depression/self-help-for-depression", "Depression"),
            new Resource("Breathing Exercises", "Simple breathing techniques for stress relief", "https://www.health.harvard.edu/mind-and-mood/relaxation-techniques-breath-control-helps-quell-errant-stress-response", "Stress Relief"),
            new Resource("Sleep Hygiene Tips", "Improve your sleep with these simple tips", "https://www.sleepfoundation.org/sleep-hygiene", "Sleep")
        ));
        return "resources";
    }

    // Simple inner class to represent resources
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
