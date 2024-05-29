package com.jwtAuth.security.services;

import com.jwtAuth.security.Util.UserNotFoundException;
import com.jwtAuth.security.Util.UserRegisteredException;
import com.jwtAuth.security.entity.Token;
import com.jwtAuth.security.entity.TokenType;
import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.request.LoginRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.response.AuthenticationResponse;
import com.jwtAuth.security.repositories.TokenRepository;
import com.jwtAuth.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest loginRequest){
        System.out.println("loginReq "+loginRequest);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        //System.out.println("user "+user);
        Map<String,Object> extraClaims = new HashMap<>();
        String jwtToken = jwtService.createToken(user,extraClaims);
        System.out.println("jwt "+jwtToken);
        saveUserToken(user,jwtToken);
        return new AuthenticationResponse(jwtToken,loginRequest.getEmail());
    }
    public AuthenticationResponse register(RegisterRequest registerRequest){
        User oldUser = userRepository.findUserByEmail(registerRequest.getEmail());
        if(oldUser != null){
            throw new UserRegisteredException("User is already registered in database..change this email!!");
        }
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .enable(false)
                .OTP(jwtService.generateOTP())
                .build();
        User savedUser = userRepository.save(user);
        Map<String,Object> extraClaims = new HashMap<>();
        String jwtToken = jwtService.createToken(user,extraClaims);
        saveUserToken(savedUser,jwtToken);
        return new AuthenticationResponse(jwtToken, registerRequest.getEmail());
    }
    private void saveUserToken(User user,String jwtToken){
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.Bearer)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);

    }
}
