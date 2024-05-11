package com.jwtAuth.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
@GetMapping(value="/rest/home")
    public ResponseEntity<String> hello(){return ResponseEntity.ok("Hello, World!");}
}
