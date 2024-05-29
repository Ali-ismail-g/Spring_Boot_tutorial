package com.todoService.todoService;

import com.todoService.todoService.entity.Item;
import com.todoService.todoService.entity.ItemDetails;
import com.todoService.todoService.entity.Priority;
import com.todoService.todoService.entity.Status;
import com.todoService.todoService.model.request.ItemDetailsRequest;
import com.todoService.todoService.model.request.LoginRequest;
import com.todoService.todoService.model.response.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodoServiceApplicationTests {
	@Autowired
	private RestTemplate restTemplate;
	private String jwtToken;

	@BeforeEach
	public void obtainJwtToken(){
		 String loginUrl = "http://localhost:8080/rest/auth/login";
		 HttpEntity<LoginRequest> entity = new HttpEntity<>(new LoginRequest("aliesmaiil94@gmail.com","555555"));
		 ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(loginUrl,entity, AuthenticationResponse.class);
		 jwtToken = response.getBody().getAccessToken();
	}

	@Test
	public void getItem() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
		String url = "http://localhost:8082/rest/item?itemId=6";
		HttpEntity<Void> headersRequest = new HttpEntity<>(headers);
		ResponseEntity<ItemDetails> response = restTemplate.exchange(url, HttpMethod.GET,headersRequest ,ItemDetails.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void addItem() throws ParseException {
//		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
//		HttpEntity<Void> headersRequest = new HttpEntity<>(headers);
		String url="http://localhost:8082/rest/item";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ItemDetails itemDetails = new ItemDetails("Going to the school", simpleDateFormat.parse("2024-11-23"), Priority.high, Status.completed);
		Item item = new Item("studying with friends", "7");
		ItemDetailsRequest itemDetailsRequest = new ItemDetailsRequest(itemDetails, item);
		HttpEntity<ItemDetailsRequest> postRequest = new HttpEntity<>(itemDetailsRequest, headers);
		try {
			ResponseEntity<ItemDetails> response = restTemplate.exchange(url, HttpMethod.POST, postRequest, ItemDetails.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

	@Test
	public void updateItem() throws ParseException {
//		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
//		HttpEntity<Void> headersRequest = new HttpEntity<>(headers);
		String url="http://localhost:8082/rest/item";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ItemDetails itemDetails = new ItemDetails("Going to the club", simpleDateFormat.parse("2024-11-23"), Priority.low, Status.not_completed);
		Item item = new Item("meeting my friends", "7");
		ItemDetailsRequest itemDetailsRequest = new ItemDetailsRequest(itemDetails, item);
		HttpEntity<ItemDetailsRequest> postRequest = new HttpEntity<>(itemDetailsRequest, headers);
		try {
			ResponseEntity<ItemDetails> response = restTemplate.exchange(url, HttpMethod.PUT, postRequest, ItemDetails.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}
	@Test
	public void deleteItem(){
//		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+jwtToken);
		HttpEntity<Void> headersRequest = new HttpEntity<>(headers);
		String url="http://localhost:8082/rest/item?itemId=8";
		try {
			ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, headersRequest, Void.class);
			System.out.println("res "+response);
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpServerErrorException e) {
			System.out.println("HTTP Status Code: " + e.getStatusCode());
			System.out.println("Response Body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

}
