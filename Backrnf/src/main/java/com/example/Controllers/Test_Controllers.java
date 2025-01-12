package com.example.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test_Controllers {

    // A simple GET endpoint to test the application
    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, " + "mouni" + "!";
    }
}

