package com.jwtAuth.security.controllers;

import com.jwtAuth.security.model.request.LoginRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.response.AuthenticationResponse;
import com.jwtAuth.security.services.AuthService;
import com.jwtAuth.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest, @RequestParam Map<String,Object> claims){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
