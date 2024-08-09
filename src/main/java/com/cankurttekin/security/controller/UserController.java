package com.cankurttekin.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/dashboard")
    public ResponseEntity<?> getUserDashboard() {
        // Access INDIVIDUAL_USER CORPORATE_USER
        return ResponseEntity.ok("User Dashboard");
    }
}
