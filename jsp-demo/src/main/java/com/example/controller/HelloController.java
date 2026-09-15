package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class HelloController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Enter Your Name");
        return "home";
    }

    @PostMapping("/greet")
    public String greetUser(@RequestParam("name") String name, Model model) {
        model.addAttribute("message", "Hello " + name);
        return "home";
    }
    
}
