package com.jwtAuth.security;

import com.jwtAuth.security.entity.Role;
import com.jwtAuth.security.entity.User;
import com.jwtAuth.security.model.request.ChangePasswordRequest;
import com.jwtAuth.security.model.request.LoginRequest;
import com.jwtAuth.security.model.request.RegisterRequest;
import com.jwtAuth.security.model.request.UserRequest;
import com.jwtAuth.security.model.response.AuthenticationResponse;
import com.jwtAuth.security.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SecurityApplicationTests {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtService jwtService;
	private String jwtToken;

	@BeforeEach
	public void obtainJwtToken(){
		String loginUrl = "http://localhost:8080/rest/auth/login";
		HttpEntity<LoginRequest> entity = new HttpEntity<>(new LoginRequest("aliesmaiil94@gmail.com","555555"));
		ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(loginUrl,entity, AuthenticationResponse.class);
		jwtToken = response.getBody().getAccessToken();
		System.out.println(jwtToken);
	}

	@Test
	public void signUpNewUser() {
		String url="http://localhost:8080/rest/auth/register";
		User user = User.builder()
				.firstName("Sherif")
				.lastName("Ismail")
				.email("aliesmaiil94@gmail.com")
				.password(bCryptPasswordEncoder.encode("123456"))
				.role(Role.user)
				.enable(false)
				.OTP(jwtService.generateOTP())
				.build();
		RegisterRequest registerRequest = new RegisterRequest(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
		try {
			ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(url, registerRequest, AuthenticationResponse.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}
	@Test
	public void updateUserInfo(){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
		System.out.println("token "+jwtToken);
//		HttpEntity<Void> headersRequest = new HttpEntity<>(headers);
		String url="http://localhost:8080/rest/auth/user";
		Map<String,String> updatedRequest = new HashMap<>();
		updatedRequest.put("firstName","Omar");
		updatedRequest.put("lastName","Tarek");
		updatedRequest.put("email","aliesmaiil94@gmail.com");
		updatedRequest.put("role", String.valueOf(Role.user));
		HttpEntity<Map<String,String>> postRequest = new HttpEntity<>(updatedRequest, headers);
		System.out.println("Post Req "+ postRequest);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, postRequest, String.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

	@Test
	public void sendEmailWithOTP(){
		String url = "http://localhost:8080/rest/auth/genOTP";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
		UserRequest userRequest = new UserRequest("aliesmaiil94@gmail.com");
		HttpEntity<UserRequest> postRequest = new HttpEntity<>(userRequest, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, postRequest, String.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}
	@Test
	public void changePassword(){
		String url = "http://localhost:8080/rest/auth/newPass";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("5744","aliesmaiil94@gmail.com","555555");
		HttpEntity<ChangePasswordRequest> postRequest = new HttpEntity<>(changePasswordRequest, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, postRequest, String.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

}
