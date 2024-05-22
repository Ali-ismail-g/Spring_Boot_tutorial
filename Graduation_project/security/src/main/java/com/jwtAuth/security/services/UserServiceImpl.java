package com.jwtAuth.security.services;

import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.request.ChangePasswordRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.request.UserRequest;
import com.jwtAuth.security.model.response.UserDetailsResponse;
import com.jwtAuth.security.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.io.StringBufferInputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;
    @Override
    public User getUserById(String token) {
        Map<String,Object> userInfo = jwtService.getUserInfo(token);
        Object myId = userInfo.get("id");
        int id = ((Number)myId).intValue();
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    @Transactional
    public String activateUser(String token, UserRequest userRequest) throws AuthenticationException {
        String email = userRequest.getEmail();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Boolean tokenValid = jwtService.isTokenValid(token,userDetails);
        if(!tokenValid){
            throw new RuntimeException("token is not valid!!");
        }
        User user = userRepository.findById(userRequest.getId()).get();
        System.out.println("User otp in userService: "+user.getOTP());
        user.setEnable(true);
        userRepository.save(user);
        return "User is enabled!!";
    }

    @Override
    public String updateUserInfo( String token, RegisterRequest registerRequest) throws AuthenticationException {
        Map<String,Object> userInfo = jwtService.getUserInfo(token);
        Object myId = userInfo.get("id");
        int id = ((Number)myId).intValue();
        User myUser = userRepository.findById(id).get();
        System.out.println("user id: "+myUser.getId());
        if (myUser==null){
            throw new RuntimeException("user is not found!!");
        }
        System.out.println("registerRequest.getFirstName()"+registerRequest.getFirstName());
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setRole(registerRequest.getRole());
        userRepository.save(myUser);
        return "user info has been updated!!";
    }

    @Override
    public String sendOTPByEmail(String token) {
        Map<String,Object> userInfo = jwtService.getUserInfo(token);
        Object myId = userInfo.get("id");
        int id = ((Number)myId).intValue();
        User myUser = userRepository.findById(id).get();
        String email = myUser.getEmail();
        System.out.println("email in db "+email);
        if(email == null){
            throw new RuntimeException("email is not registered!!");
        }
        String otpCode = myUser.getOTP();
        System.out.println("otp in user service: "+otpCode);
        String subject = "sending OTP for changing password";
        String body = "Hello, here's the generated OTP:   "+otpCode;
        emailSenderService.sendEmail(email,subject,body);
        return "Check your email for OTP!!";
    }

    @Override
    public String changePassword(String token, ChangePasswordRequest changePasswordRequest) {
        Map<String,Object> userInfo = jwtService.getUserInfo(token);
        Object myId = userInfo.get("id");
        int id = ((Number)myId).intValue();
        User myUser = userRepository.findById(id).get();
        System.out.println("otp from user: "+myUser.getOTP());
        System.out.println("otp from body: "+changePasswordRequest.getOtpCode());
//        if(myUser.getOTP() != changePasswordRequest.getOtpCode()){
//            throw new RuntimeException("otp is not correct!!");
//        }
//        if(myUser.getEmail() != changePasswordRequest.getEmail()){
//            throw new RuntimeException("email is not registered!!");
//        }
        myUser.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getPassword()));
        userRepository.save(myUser);
        return "password is changed successfully!!";
    }
}
