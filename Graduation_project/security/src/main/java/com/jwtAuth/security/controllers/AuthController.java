package com.jwtAuth.security.controllers;

import com.jwtAuth.security.model.request.LoginRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.response.AuthenticationResponse;
import com.jwtAuth.security.model.response.UserDetailsResponse;
import com.jwtAuth.security.services.AuthService;
import com.jwtAuth.security.services.JwtService;
import com.jwtAuth.security.services.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Map;

@Controller
@RequestMapping("/rest/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest, @RequestParam Map<String,Object> claims){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @GetMapping(value="/details")
    public ResponseEntity<Claims> checkUserDetails(@RequestHeader("Authorization") String bearerToken){return ResponseEntity.ok(jwtService.getUserInfo(bearerToken));}

    @GetMapping(value="/valid")
    public ResponseEntity<Boolean> checkValidityToken(@RequestHeader("Authorization") String bearerToken,@RequestParam String email) throws AuthenticationException {
        String accessToken = jwtService.extractToken(bearerToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return ResponseEntity.ok(jwtService.isTokenValid(accessToken,userDetails));
    }
}
