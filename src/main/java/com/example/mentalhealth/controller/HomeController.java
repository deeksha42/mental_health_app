package com.example.mentalhealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {

    @GetMapping
    public String home() {
        return "index"; // This will return index.html from templates
    }
}
