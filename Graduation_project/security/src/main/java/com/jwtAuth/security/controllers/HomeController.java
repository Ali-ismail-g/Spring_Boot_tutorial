package com.jwtAuth.security.controllers;

import com.jwtAuth.security.model.response.UserDetailsResponse;
import com.jwtAuth.security.services.AuthService;
import com.jwtAuth.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class HomeController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;
@GetMapping(value="/home")
    public ResponseEntity<String> hello(){return ResponseEntity.ok("Hello, World!");}
}

