package dev_davisantos.spring_security_studies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSimpleController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route is working!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute() {
        return ResponseEntity.ok("Private route is working!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRoute() {
        return ResponseEntity.ok("Admin route is working!");
    }

}
