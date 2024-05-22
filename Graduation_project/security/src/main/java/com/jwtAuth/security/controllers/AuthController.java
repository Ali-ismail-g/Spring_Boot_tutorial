package com.jwtAuth.security.controllers;

import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.request.ChangePasswordRequest;
import com.jwtAuth.security.model.request.LoginRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.request.UserRequest;
import com.jwtAuth.security.model.response.AuthenticationResponse;
import com.jwtAuth.security.services.AuthService;
import com.jwtAuth.security.services.JwtService;
import com.jwtAuth.security.services.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    UserService userService;

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
    @PutMapping(value="/activate")
    public ResponseEntity<String> activateUser(@RequestHeader("Authorization") String bearerToken, @RequestBody UserRequest userRequest) throws AuthenticationException {
        System.out.println("user otp in controller: "+userRequest.getOtpCode());
        String accessToken = jwtService.extractToken(bearerToken);
        return ResponseEntity.ok(userService.activateUser(accessToken,userRequest));
    }
    @GetMapping(value="/user")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String bearerToken){return ResponseEntity.ok(userService.getUserById(bearerToken));}
    @PutMapping(value="/user")
    public ResponseEntity<String> updateUserInfo(@RequestHeader("Authorization") String bearerToken, @RequestBody RegisterRequest registerRequest) throws AuthenticationException {
        System.out.println("Register Request in body: "+registerRequest);
        return ResponseEntity.ok(userService.updateUserInfo(bearerToken,registerRequest));
    }
    @PostMapping(value = "/genOTP")
    public ResponseEntity<String> sendOTPByEmail(@RequestHeader("Authorization") String bearerToken){
        return ResponseEntity.ok(userService.sendOTPByEmail(bearerToken));
    }

    @PostMapping(value = "/newPass")
    public ResponseEntity<String> createNewPassword(@RequestHeader("Authorization") String bearerToken,@RequestBody ChangePasswordRequest changePasswordRequest){
        return ResponseEntity.ok(userService.changePassword(bearerToken,changePasswordRequest));
    }

}
