package com.jwtAuth.security.services;

import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.request.ChangePasswordRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.request.UserRequest;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public interface UserService {
    public User getUserById(String token);
    public String activateUser(String token, UserRequest userRequest) throws AuthenticationException;
    public String updateUserInfo(String token, RegisterRequest registerRequest) throws AuthenticationException;
    public String sendOTPByEmail(String token);
    public String changePassword(String token, ChangePasswordRequest changePasswordRequest);
}
